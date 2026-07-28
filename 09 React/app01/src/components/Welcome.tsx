import { Component } from "react";

const titles = [
    {title:"Mr.",label:"GentelMan"},
    {title:"Mrs.",label:"Lady"},
    {title:"Dr.",label:"Doctor"},
    {title:"Prof.",label:"Professor"},    
    {title:"Master.",label:"Boy Child"},    
    {title:"Baby.",label:"Girl Child"},    
    {title:"",label:"--SELECT---"}
];

class Welcome extends Component<{},{title:string,userName:string}> {
  constructor(props:{}){
    super(props);
    this.state = {
        title:"",
        userName: "SomeBody"
    };
  }

  render() {
    const {title,userName} = this.state;

    return (
      <section>
        <h3> Welcome {title} {userName}</h3>
        <form>
            <label>
                title: 
                <select 
                    value={title} 
                    onChange={ e => this.setState({title:e.target.value})}>
                    {
                        titles.map( t => (
                            <option key={t.title} value={t.title}>{t.label}</option>                        
                        ))
                    }                    
                </select>
            </label>
            <label>
                User Name: 
                <input type="text" value={userName} 
                 onChange={ e => this.setState({userName:e.target.value})}/>
            </label>
        </form>
      </section>
    );
  }
}

export default Welcome;
