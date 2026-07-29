import { Alert, Button, Card, CardBody, CardHeader, Table } from "react-bootstrap";
import type { Contact } from "../lib/models/Contact";
import { useState } from "react";
import { Link } from "react-router";

function ContactsList() {

    const [list, setList] = useState<Contact[]>([
        { contactId: 1, fullName: "Vamsy", mobileNumber: "9999999991", mailId: "v@g.com", dateOfBirth: "1985-06-11" },
        { contactId: 2, fullName: "Murthy", mobileNumber: "9999999992", mailId: "m@g.com", dateOfBirth: "1985-06-12" },
        { contactId: 3, fullName: "Suresh", mobileNumber: "9999999993", mailId: "s@g.com", dateOfBirth: "1985-06-13" },
        { contactId: 4, fullName: "Ramesh", mobileNumber: "9999999994", mailId: "r@g.com", dateOfBirth: "1985-06-14" },
    ]);

    const removeContact = (id: number) => {
        setList(cv => cv.filter(cx => cx.contactId !== id));
    }

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
                                                onClick={_e => removeContact(cx.contactId)}>
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
        </Card>
    )
}

export default ContactsList;