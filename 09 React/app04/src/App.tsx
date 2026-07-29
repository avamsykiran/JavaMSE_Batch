import Header from "./components/Header";
import Welcome from "./components/Welcome";
import ItemCount from "./components/ItemCounter";
import { Col, Container, Row } from "react-bootstrap";

function App() {
  return (
    <>
      <Header pageTitle="My SPA 4.0" />
      <Container fluid className="p-4">
        <h3>This is my first page of my first ReactJs SPA</h3>
        <Row className="p-4">
          <Col className="m-2">
            <Welcome />
          </Col>
          <Col className="m-2">
            <ItemCount />  
          </Col>
        </Row>
      </Container>
    </>
  );
}

export default App;
