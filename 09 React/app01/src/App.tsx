import { Component } from "react";
import Header from "./components/Header";
import Welcome from "./components/Welcome";
import ItemCount from "./components/ItemCounter";

class App extends Component<{},{}> {
  constructor(props:{}){
    super(props);
    this.state = {};
  }

  render() {
    return (
      <>
        <Header pageTitle="My SPA 1.0" />
        <main style={{padding:"10px"}}>
          <h3>This is my first page of my first ReactJs SPA</h3>
          <Welcome />
          <ItemCount />
        </main>
      </>
    );
  }
}

export default App;
