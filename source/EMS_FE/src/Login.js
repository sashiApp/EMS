import React, { Component } from 'react';
import {backendUrl} from './constants';
import './App.css';
import './Login.css';
import axios from 'axios';
class Login extends Component{
        constructor(props){
                super(props);
                console.log("Login props"+props.token);
                this.state={
                        login:[],isHidden:false,username:"",password:"",satus:""
                }
                 this.closeDiv =this.closeDiv.bind(this);
                this.openDiv =this.openDiv.bind(this);
                this.doLogin =this.doLogin.bind(this);
        }
       
  onChange = (e) => {
        // Because we named the inputs to match their corresponding values in state, it's
        // super easy to update the state
        const state = this.state
        state[e.target.name] = e.target.value;
        this.setState(state);
      }

  
    render(){
            const {isHidden} = this.state;
        return(
                
            <div className="LoginDiv">
            <div className="divTableCE">
            <div className="divRowHeader">
            <div className="divCellHeaderLeft">
                <div>
                                Login Page
                    </div>    
                </div>    
            <div className="divCellHeaderRight">
           <div className="openClose">
        <a href="#" onClick={this.closeDiv}>(-)</a> &nbsp; &nbsp;    <a href="#" onClick={this.openDiv}>(+)</a>
                    </div>    
                    </div> 
                </div>
                
            <div className={`div${isHidden ?'Hide':'Show'}` }>
             
              <div className="divRowCE">
                    <div className="divCellCE">
                        {
                         this.state.status   ? "Status : ":""
                    } {this.state.status} 
                      
                    </div>
            </div>
            <div className="divRowCE">
                    <div className="divCellCE">
                            User ID:   
                    </div>
                     <div className="divCellMCE">
                            <input type="text" name="username"  onChange={this.onChange}/>   
                    </div> 
            </div>
              <div className="divRowCE">
                    <div className="divCellCE">
                            Password:   
                    </div>  
                    <div className="divCellMCE">
                            <input type="password" name="password"  onChange={this.onChange}/>   
                    </div>  
            </div>
               <div className="divRowCE">
                     <div className="divCellCE">
                            
                    </div> 
                    <div className="divCellMCE">
                            <button  name="Login" onClick={this.doLogin}>Login </button>  
                    </div>  
            </div>
             <br/>
            
            </div>   
            </div>   
            </div>
            
        );
    }

    openDiv(){
            console.log("opendiv called!");
            this.setState({
                         isHidden: false
                 })
    }
    closeDiv(){
            console.log("closediv called!");
            this.setState({
                         isHidden: true
                })
    }
    doLogin(){
            let userid = this.state.username;
            let password = this.state.password;
           
        axios.post(`${backendUrl}/login`, {username:userid,password:password})
        .then( (response) => {
            console.log(response);
            this.props.setToken(response.data.data);
            this.setState ({status:response.data.emsStatus.status});
        })
        .catch((error) => {
            console.log(error);
             this.setState ({status:"NOT OK"});
     });
    }
}
export default Login;