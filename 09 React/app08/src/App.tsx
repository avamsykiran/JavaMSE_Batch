import { Container } from "react-bootstrap";
import { BrowserRouter, Route, Routes } from "react-router";
import Header from "./components/Header";
import ContactsList from "./components/ContactsList";
import ContactForm from "./components/ContactForm";
import Home from "./components/Home";
import PathNotFound from "./components/PathNotFound";
import Login from "./components/Login";
import Registration from "./components/Registration";
import { ProtectedRoute } from "./components/ProtectedRoute";

function App() {
  return (
    <BrowserRouter>
      <Header appTitle="Address Book" />
      <Container fluid className="m-4 p-4 mx-auto">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Registration />} />

          <Route element={<ProtectedRoute />}>
            <Route path="/list" element={<ContactsList />} />
          </Route>
          
          <Route element={<ProtectedRoute requiredRole="ROLE_ADMIN" />}>
            <Route path="/new" element={<ContactForm />} />
          </Route>
          
          <Route element={<ProtectedRoute requiredRole="ROLE_ADMIN" />}>
            <Route path="/edit/:id" element={<ContactForm />} />      
          </Route>
          
          {/* '*' indicates a path that is not mapped. */}
          <Route path="*" element={<PathNotFound />} />          
        </Routes>
      </Container>
    </BrowserRouter>
  );
}

export default App
