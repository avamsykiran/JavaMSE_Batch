import Header from "./components/Header";
import Welcome from "./components/Welcome";
import ItemCount from "./components/ItemCounter";

function App() {
  return (
    <>
      <Header pageTitle="My SPA 3.0" />
      <main className="container-fluid p-4">
        <h3>This is my first page of my first ReactJs SPA</h3>
        <Welcome />
        <ItemCount />
      </main>
    </>
  );
}

export default App;
