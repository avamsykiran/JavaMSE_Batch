import { useState } from "react";
import { Card, CardBody, CardHeader } from "react-bootstrap";

const titles = [
  { title: "Mr.", label: "GentelMan" },
  { title: "Mrs.", label: "Lady" },
  { title: "Dr.", label: "Doctor" },
  { title: "Prof.", label: "Professor" },
  { title: "Master.", label: "Boy Child" },
  { title: "Baby.", label: "Girl Child" },
  { title: "", label: "--SELECT---" }
];

function Welcome() {

  const [title, setTitle] = useState<string>("");
  const [userName, setUserName] = useState<string>("SomeBody");

  return (
    <Card bg="primary">
      <CardHeader>
        <h3> Welcome {title} {userName}</h3>
      </CardHeader>
      <CardBody>
        <form>
          <label>
            title:
            <select
              value={title}
              onChange={e => setTitle(e.target.value)}>
              {
                titles.map(t => (
                  <option key={t.title} value={t.title}>{t.label}</option>
                ))
              }
            </select>
          </label>
          <label>
            User Name:
            <input type="text" value={userName}
              onChange={e => setUserName(e.target.value)} />
          </label>
        </form>
      </CardBody>
    </Card>
  );
}

export default Welcome;
