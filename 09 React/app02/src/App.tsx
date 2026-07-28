import Header from "./components/Header";
import Welcome from "./components/Welcome";
import ItemCount from "./components/ItemCounter";

function App() {
  return (
    <>
      <Header pageTitle="My SPA 2.0" />
      <main style={{ padding: "10px" }}>
        <h3>This is my first page of my first ReactJs SPA</h3>
        <Welcome />
        <ItemCount />
      </main>
    </>
  );
}

export default App;
