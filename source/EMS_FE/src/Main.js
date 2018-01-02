import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import './App.css';
import axios from 'axios';
import ListEmployee from './ListEmployees';
import CreateEmployee from './CreateEmployee';
import Login from './Login';

/* Main Component to act as a container of sub components like Employee list/create
*/
class Main extends Component{

constructor(props){
    super(props);
    
    this.state = {token:0,companyId:0, companyName:"", refresh:false};


}

setToken= (tokenVal)=>
{
    console.log("Token Value:"+tokenVal);
    var company = (tokenVal+"").split(".")[0];
    
    console.log("Company:"+company);
    this.setState({token:tokenVal,companyId:company});
}

refreshEmployees = () =>{
this.setState({refresh:true});
}
render(){
    return(
        <div >
        <Login setToken={this.setToken} />
        <ListEmployee token={this.state.token} refresh ={this.state.refresh} companyId={this.state.companyId}/>
        <CreateEmployee token={this.state.token} refresh ={this.refreshEmployees} companyId={this.state.companyId}/>
        </div>
    );
}
}

export default Main;