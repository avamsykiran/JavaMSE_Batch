import { Alert, Button, Card, CardBody, CardFooter, CardHeader, Table } from "react-bootstrap";
import type { Contact } from "../lib/models/Contact";
import { Link } from "react-router";
import { useDispatch, useSelector } from "react-redux";
import { selectAllContacts, selectContactsCount } from "../lib/reduxState/selectors";
import type { AppDispatch } from "../lib/reduxState/appStore";
import { deleteContact } from "../lib/reduxState/contactsSlice";

function ContactsList() {

    const list:Contact[] = useSelector(selectAllContacts);        
    const count:number = useSelector(selectContactsCount);
    const dispatch:AppDispatch = useDispatch();

    return (
        <Card>
            <CardHeader>Contacts List</CardHeader>
            <CardBody>
                {
                    list && list.length > 0 ? (
                        <Table striped hover bordered>
                            <thead>
                                <th>Contact#</th>
                                <th>Name</th>
                                <th>Mobile</th>
                                <th>Mail</th>
                                <th>DoB</th>
                                <th>Actions</th>
                            </thead>
                            <tbody>
                                {list.map(cx => (
                                    <tr key={cx.contactId}>
                                        <td>{cx.contactId}</td>
                                        <td>{cx.fullName}</td>
                                        <td>{cx.mobileNumber}</td>
                                        <td>{cx.mailId}</td>
                                        <td>{cx.dateOfBitth}</td>
                                        <td>
                                            <Link to={`/edit/${cx.contactId}`}
                                                className="btn btn-sm btn-info me-1">
                                                <i className="bi-pen" /> EDIT
                                            </Link>
                                            <Button variant="danger" size="sm"
                                                onClick={_e => dispatch(deleteContact(cx.contactId)) }>
                                                <i className="bi-trash" /> DELETE
                                            </Button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                    ) : (
                        <Alert variant="info">
                            <p>No Data Found</p>
                        </Alert>
                    )
                }
            </CardBody>
            <CardFooter className="text-center">
                We have <strong>{count}</strong> record(s).
            </CardFooter>
        </Card>
    )
}

export default ContactsList;