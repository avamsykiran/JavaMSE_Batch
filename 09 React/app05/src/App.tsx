import { Container } from "react-bootstrap";
import { BrowserRouter, Route, Routes } from "react-router";
import Header from "./components/Header";
import ContactsList from "./components/ContactsList";
import ContactForm from "./components/ContactForm";
import Home from "./components/Home";
import PathNotFound from "./components/PathNotFound";

function App() {
  return (
    <BrowserRouter>
      <Header />
      <Container fluid className="m-4 p-4 mx-auto">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/list" element={<ContactsList />} />
          <Route path="/new" element={<ContactForm />} />
          <Route path="/edit/:id" element={<ContactForm />} />      
          <Route path="*" element={<PathNotFound />} />          
        </Routes>
      </Container>
    </BrowserRouter>
  );
}

export default App
