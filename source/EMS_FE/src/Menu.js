import React, { Component } from 'react';
import './App.css';

class Menu extends Component{
    render(){
        return(
        <div className="Menu">   
        <br/> 
        <a href="All Employees"> All Employees</a>
        &nbsp;&nbsp;<a href="create"> Create an Employee</a>
         </div>
        ); 
    }   
}
export default Menu;