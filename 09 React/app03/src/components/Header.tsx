function Header({pageTitle}: { pageTitle: string }) {
    return (
        <header className="navbar navbar-expand-sm navbar-dark bg-dark">
            <div className='container-fluid'>
                <a className='navbar-brand' href="#">{pageTitle}</a>
            </div>
        </header>
    );
}

export default Header;
