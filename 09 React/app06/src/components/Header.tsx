import { Container, Nav, Navbar, NavbarBrand, NavbarCollapse, NavbarToggle, NavLink } from "react-bootstrap";
import { useLocation } from "react-router";

function Header({ appTitle }: { appTitle: string }) {

    const links = [
        { path: "/", text: "Home" },
        { path: "/list", text: "Contacts List" },
        { path: "/new", text: "New Contact" },
    ];

    const { pathname } = useLocation();

    return (
        <Navbar expand="sm" bg="dark" data-bs-theme="dark">

            <Container>

                <NavbarBrand href="#">{appTitle}</NavbarBrand>

                <NavbarToggle aria-controls="my-nav" />

                <NavbarCollapse id="my-nav">
                    <Nav className="me-auto">
                        {
                            links.map(lx => (
                                <NavLink 
                                    key={lx.path}
                                    href={lx.path} className={pathname === lx.path ? "active":""}>
                                    {lx.text}
                                </NavLink>
                            ))
                        }
                    </Nav>
                </NavbarCollapse>

            </Container>

        </Navbar>
    )
}

export default Header;