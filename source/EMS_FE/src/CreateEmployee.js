import React, { Component } from 'react';
import {backendUrl} from './constants';
import './EmployeeView.css';
import axios from 'axios';
/* Component with employee form to create an employee
*/
class CreateEmployee extends Component{
        constructor(props){
                super(props);
               this.state={
                        companyid:0,isHidden:false,status:"",employee:{name:"",surname:"",
                        email:"",address:"",salary:""}
                }
                this.createEmployee = this.createEmployee.bind(this);
                this.closeDiv =this.closeDiv.bind(this);
                this.openDiv =this.openDiv.bind(this);
        }

  componentWillReceiveProps(nextProps){
                if (nextProps.companyId !=0){
                this.state["companyid"]= nextProps.companyId;
                this.state["token"]= nextProps.token;
                }
        }
    render(){
             const {isHidden} = this.state;
        return(
            <div className="createEmployee">
            
            <div className="divTableCE">
        
            <div className="divRowHeader">
            <div className="divCellHeaderLeft">
                <div>
                                Create an Employee
                    </div>    
                </div>    
                <div className="divCellHeaderRight">
                        <div className="openClose">
                        <a href="#" onClick={this.closeDiv}>(-)</a> &nbsp; &nbsp;    <a href="#" onClick={this.openDiv}>(+)</a>
                        </div>    
                </div> 
                </div>
                 <div className="divRowCE">
                    <div className="divCellCE">
                    {
                         this.state.status   ? "Status : ":""
                    }
                       {this.state.status} 
                    </div>
                     
            </div>
            <div className={`div${isHidden ?'Hide':'Show'}` }>
            <div className="divRowCE">
                    <div className="divCellCE">
                            Name:   
                    </div>
                     <div className="divCellMCE">
                              <input type="text" name ="name" onChange={this.onChange} />  
                    </div> 
            </div>
              <div className="divRowCE">
                    <div className="divCellCE">
                            Surname:   
                    </div>  
                    <div className="divCellMCE">
                            <input type="text" name="surname" onChange={this.onChange}/>   
                    </div>  
            </div>
                <div className="divRowCE">    
                    <div className="divCellCE">
                           Email:   
                    </div> 
                    <div className="divCellMCE">
                            <input type="text" name="email" onChange={this.onChange}/>  
                    </div> 
                </div>
                  <div className="divRowCE">    
                    <div className="divCellCE">
                           Address:   
                    </div> 
                    <div className="divCellMCE">
                            <input type="text" name="address" onChange={this.onChange}/>  
                    </div> 
                </div>
                <div className="divRowCE">    
                    <div className="divCellCE">
                           Salary:   
                    </div> 
                    <div className="divCellMCE">
                            <input type="text" name="salary" onChange={this.onChange}/>   
                </div>  
                   </div>
                   <div className="divRowCE">    
                      <div className="divCellCE">
                          
                    </div> 
                    <div className="divCellButton">
                            <button name="create" onClick={this.createEmployee}>  Create </button>
                </div>  
                   </div>
                   </div>
            </div>
            </div>
        );
    }


   onChange = (e) => {
        const state = this.state
        state.employee[e.target.name] = e.target.value;
        this.setState(state);
      }

createEmployee= () =>{
      //  let headers = {AccessToken:token};
        const  employee= this.state.employee;
        const companyId = this.state["companyid"];
        const accessToken = this.state["token"];
         let headers = {headers:{AccessToken:accessToken}};
        employee.company = {id:companyId};
        console.log("Employee:"+employee);
    axios.post(`${backendUrl}/employee/`,employee,headers)
        .then( (response) => {
            console.log(response);
            this.setState ({status:response.data.emsStatus.status});
            this.props.refresh();
  
        })
        .catch(function (error) {
            console.log(error);
            this.setState ({status:"NOT OK"});
     }); 
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
}



export default CreateEmployee;