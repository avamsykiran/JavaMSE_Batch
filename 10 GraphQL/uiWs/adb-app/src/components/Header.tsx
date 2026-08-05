import { useContext } from "react";
import { Button, Container, Nav, Navbar, NavbarBrand, NavbarCollapse, NavbarToggle, NavLink } from "react-bootstrap";
import { useLocation } from "react-router";
import { ThemeContext } from "../lib/context/ThemeProvider";

function Header({ appTitle }: { appTitle: string }) {

    const links = [
        { path: "/", text: "Home" },
        { path: "/list", text: "Contacts List" },
        { path: "/new", text: "New Contact" },
    ];

    const { pathname } = useLocation();

    const { theme, toggleTheme } = useContext(ThemeContext) ?? {};

    return (
        <Navbar expand="sm" bg={theme=="dark"?"light":"dark"} data-bs-theme={theme=="dark"?"light":"dark"}>

            <Container>

                <NavbarBrand href="#">{appTitle}</NavbarBrand>

                <NavbarToggle aria-controls="my-nav" />

                <NavbarCollapse id="my-nav">
                    <Nav className="me-auto">
                        {
                            links.map(lx => (
                                <NavLink
                                    key={lx.path}
                                    href={lx.path} className={pathname === lx.path ? "active" : ""}>
                                    {lx.text}
                                </NavLink>
                            ))
                        }
                    </Nav>
                    <div className="d-flex ms-auto">
                        <Button
                            variant={theme === 'dark' ? 'outline-light' : 'outline-dark'}
                            onClick={toggleTheme}
                        >
                            {theme === 'dark' ? '☀️ Light Mode' : '🌙 Dark Mode'}
                        </Button>
                    </div>
                </NavbarCollapse>

            </Container>

        </Navbar>
    )
}

export default Header;