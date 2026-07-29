import { Container, Navbar, NavbarBrand } from "react-bootstrap";

function Header({pageTitle}: { pageTitle: string }) {
    return (
        <Navbar expand="sm" bg="dark" data-bs-theme="dark">
            <Container>
                <NavbarBrand>{pageTitle}</NavbarBrand>
            </Container>
        </Navbar>
    );
}

export default Header;
