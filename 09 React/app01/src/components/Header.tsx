import { Component, type ReactNode } from "react";
import '../assets/styles/Header.css';

class Header extends Component<{pageTitle:string},{}> {
    constructor(props){
        super(props);
        this.state = {};
    }

    render(): ReactNode {
        return(
            <header className="header">
                <h3>{this.props.pageTitle}</h3>
            </header>
        );
    }
}

export default Header;
