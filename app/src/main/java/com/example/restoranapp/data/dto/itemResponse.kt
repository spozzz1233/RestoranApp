package com.example.restoranapp.data.dto

import com.example.realestateapp.data.network.Response
import com.example.restoranapp.domain.model.MenuItem

class itemResponse(val results: ArrayList<MenuItem>): Response()
