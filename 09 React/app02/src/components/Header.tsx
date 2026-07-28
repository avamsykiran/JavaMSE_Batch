import '../assets/styles/Header.css';

//function Header(props: { pageTitle: string }) {
function Header({pageTitle}: { pageTitle: string }) {
    return (
        <header className="header">
            <h3>{pageTitle}</h3>
        </header>
    );
}

export default Header;
