package com.example.autorus.di
import android.annotation.SuppressLint
import com.example.realestateapp.data.network.NetworkClient
import com.example.realestateapp.data.network.RetrofitNetworkClient
import com.example.restoranapp.data.Item.ItemRepository
import com.example.restoranapp.data.Item.impl.ItemRepositoryImpl
import com.example.restoranapp.data.api.RestoranApi
import com.example.restoranapp.domain.item.ItemInteractor
import com.example.restoranapp.domain.item.impl.ItemInteractorImpl
import com.example.restoranapp.ui.order.OrderViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

val mainModule = module {
    single<ItemRepository> { ItemRepositoryImpl(get()) }
    single<NetworkClient> { RetrofitNetworkClient(get()) }
    single<ItemInteractor> { ItemInteractorImpl(get()) }
    single<RestoranApi> {
        Retrofit.Builder()
            .baseUrl("https://10.0.2.2:7001")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(RestoranApi::class.java)
    }

    single<OkHttpClient>{
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .sslSocketFactory(get(), get<Array<TrustManager>>()[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
            .build()
    }

    single<HttpLoggingInterceptor>{
        HttpLoggingInterceptor().also{
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<SSLSocketFactory>{get<SSLContext>().socketFactory}

    single<SSLContext>{
        SSLContext.getInstance("SSL").also {
            it.init(null, get<Array<TrustManager>>(), SecureRandom())
        }
    }

    single<Array<TrustManager>>{
        arrayOf<TrustManager>(@SuppressLint("CustomX509TrustManager")
        object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })
    }

    viewModel { OrderViewModel(get()) }

}
