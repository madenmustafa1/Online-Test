package com.maden.testapp.viewmodel

import android.view.View
import kotlinx.android.synthetic.main.fragment_take_test.view.*

class CheckControl(val view: View) {

    fun aCheck(){
        if(view.aCheckBox.isChecked){
            view.bCheckBox.isChecked = false
            view.cCheckBox.isChecked = false
            view.dCheckBox.isChecked = false } }

    fun bCheck(){
        if(view.bCheckBox.isChecked) {
            view.aCheckBox.isChecked = false
            view.cCheckBox.isChecked = false
            view.dCheckBox.isChecked = false } }

    fun cCheck(){
        if(view.cCheckBox.isChecked){
            view.aCheckBox.isChecked = false
            view.bCheckBox.isChecked = false
            view.dCheckBox.isChecked = false } }

    fun dCheck(){
        if(view.dCheckBox.isChecked){
            view.aCheckBox.isChecked = false
            view.bCheckBox.isChecked = false
            view.cCheckBox.isChecked = false } }

    fun isClear(){
        view.aCheckBox.isChecked = false
        view.bCheckBox.isChecked = false
        view.cCheckBox.isChecked = false
        view.dCheckBox.isChecked = false }
}