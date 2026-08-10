import { Card, CardBody, CardHeader, CardText, CardTitle } from "react-bootstrap";
import { selectCurrentUser } from "../lib/reduxState/selectors";
import { useSelector } from "react-redux";

function Home() {

    const user = useSelector(selectCurrentUser);

    return (
        <Card>
            <CardHeader>Home Page</CardHeader>
            <CardBody>
                <CardTitle>Welcome { user?.username } !</CardTitle>
                <CardText>
                    This application is being developed for the purpose of demonstrating a few ReactJS Concepts like the following
                    <ol>
                        <li>Components and Props</li>
                        <li>Routing</li>
                        <li>Working with React Bootstrap </li>
                        <li>Local (Component Level) State Management</li>
                        <li>App Level (Global) State Management using RTK</li>
                        <li>Working with Form Hook and YUP</li>
                    </ol>
                </CardText>
            </CardBody>
        </Card>
    )
}

export default Home;