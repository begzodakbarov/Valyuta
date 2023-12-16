package com.example.valyuta

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.valyuta.adapter.KursAdapter
import com.example.valyuta.databinding.ActivityHomeBinding
import com.example.valyuta.models.MainValyuta
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import org.json.JSONArray

class HomeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    lateinit var requestQueue: RequestQueue
    lateinit var kursadapter: KursAdapter
    var url = "https://nbu.uz/uz/exchange-rates/json/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: onCreate home")

        requestQueue = Volley.newRequestQueue(this)

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            object : Response.Listener<JSONArray> {
                override fun onResponse(response: JSONArray?) {

                    val type = object : TypeToken<ArrayList<MainValyuta>>(){}.type
                    val list = Gson().fromJson<ArrayList<MainValyuta>>(response.toString(), type)

                    binding.valyutaCursUsa.text = list[23].cb_price
                    binding.valyutaCursRus.text = list[18].cb_price
                    binding.valyutaCurs.text = list[7].cb_price

                    binding.usa.setOnClickListener {
                            var intent = Intent(this@HomeActivity,AboutKurs::class.java)
                            intent.putExtra("tittle", list[23].title)
                            intent.putExtra("code", list[23].code)
                            intent.putExtra("price", list[23].cb_price)
                            intent.putExtra("buy", list[23].nbu_buy_price)
                            intent.putExtra("cell", list[23].nbu_cell_price)
                            intent.putExtra("date", list[23].date)
                            startActivity(intent)

                        }


                    binding.rus.setOnClickListener {
                        var intent = Intent(this@HomeActivity,AboutKurs::class.java)
                        intent.putExtra("tittle", list[18].title)
                        intent.putExtra("code", list[18].code)
                        intent.putExtra("price", list[18].cb_price)
                        intent.putExtra("buy", list[18].nbu_buy_price)
                        intent.putExtra("cell", list[18].nbu_cell_price)
                        intent.putExtra("date", list[18].date)
                        startActivity(intent)
                    }

                    binding.yevro.setOnClickListener {
                        var intent = Intent(this@HomeActivity,AboutKurs::class.java)
                        intent.putExtra("tittle", list[7].title)
                        intent.putExtra("code", list[7].code)
                        intent.putExtra("price", list[7].cb_price)
                        intent.putExtra("buy", list[7].nbu_buy_price)
                        intent.putExtra("cell", list[7].nbu_cell_price)
                        intent.putExtra("date", list[7].date)
                        startActivity(intent)
                    }

                    kursadapter = KursAdapter(list,object : KursAdapter.Click{
                        override fun click(position: Int, list: ArrayList<MainValyuta>) {
                            var intent = Intent(this@HomeActivity,AboutKurs::class.java)
                            intent.putExtra("tittle", list[position].title)
                            intent.putExtra("code", list[position].code)
                            intent.putExtra("price", list[position].cb_price)
                            intent.putExtra("buy", list[position].nbu_buy_price)
                            intent.putExtra("cell", list[position].nbu_cell_price)
                            intent.putExtra("date", list[position].date)
                            startActivity(intent)

                        }
                    })
                    binding.rvKurslar.adapter = kursadapter

                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                }
            })

        jsonArrayRequest.tag = "tag1" //tag berilyapti
        requestQueue.add(jsonArrayRequest)

    }
}