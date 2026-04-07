import { useEffect, useState } from "react";
import axios from "axios";

const API = "http://localhost:8082/users";

export default function App() {
  
  // Store list of users from database
  const [users, setUsers] = useState([]);

  // Store form input values
  const [form, setForm] = useState({ 
    name: "",age: "",email: "",city: "",occupation: ""});
  
  // Store uploaded excel file
  const [file, setFile] = useState(null);

  // Store which user is being edited (null means not editing)
  const [editId, setEditId] = useState(null);

  // Submit form on Enter key press
  const handleKeyPress = (e) => {
  if (e.key === "Enter" && form.name && form.age && form.email && form.city && form.occupation) {add();}};
  
  // Fetch all users from backend
  const load = () => axios.get(API).then(r => setUsers(r.data));

  // Load users when page first opens
  useEffect(() => { load(); }, []);

  // Add new user
  const add = () => axios.post(API, form).then(() => {
      setForm({ name: "", age: "", email: "", city: "", occupation: "" });
      alert("User added successfully");
      load();});
  
  // Delete user by id (with confirmation)
  const del = (id) => {
  if (window.confirm("Confirm Delete?")) {
    axios.delete(`${API}/${id}`).then(load);}};

  // Fill form with selected user data for editing
  const edit = (u) => {
    setEditId(u.id);
    setForm({ 
      name: u.name, 
      age: u.age, 
      email: u.email, 
      city: u.city, 
      occupation: u.occupation 
    });
  };

  // Update existing user
  const update = () => {
    axios.put(`${API}/${editId}`, form).then(() => {
      setEditId(null);
      setForm({ name: "", age: "", email: "", city: "", occupation: "" });
      alert("User updated successfully");
      load();
    });
  };

  // Upload excel file to backend
  const upload = () => {
    const formData = new FormData();
    formData.append("file", file);

    axios.post(`${API}/upload`, formData, {
      headers: { "Content-Type": "multipart/form-data" }
    }).then(() => {
  alert("File uploaded successfully");
  load();});
  };

  return (
  <div style={{ 
  padding: 24,
  maxWidth: "600px",
  margin: "auto",
  fontFamily: "Arial"
}}>

  <h1>{editId ? "Edit User" : "Users"}</h1>

  {/* Name Input  */}
    <input 
     value={form.name}
  placeholder="Name"
  style={{ margin: "5px", padding: "8px", width: "100%" }}
  onChange={e => setForm({...form, name: e.target.value})}
  onKeyDown={handleKeyPress}
/>

  {/* Age input */}
<input 
  value={form.age} 
  placeholder="Age"
  style={{ margin: "5px", padding: "8px", width: "100%" }}
  onChange={e => setForm({...form, age: e.target.value})}
  onKeyDown={handleKeyPress}
/>

  {/* Email input */}
<input
  value={form.email}
  placeholder="Email"
  style={{ margin: "5px", padding: "8px", width: "100%" }}
  onChange={e => setForm({...form, email: e.target.value})}
  onKeyDown={handleKeyPress}

/>

  {/* City input */}
<input
  value={form.city}
  placeholder="City"
  style={{ margin: "5px", padding: "8px", width: "100%" }}
  onChange={e => setForm({...form, city: e.target.value})}
  onKeyDown={handleKeyPress}

/>

  {/* Occupation input */}
<input
  value={form.occupation}
  placeholder="Occupation"
  style={{ margin: "5px", padding: "8px", width: "100%" }}
  onChange={e => setForm({...form, occupation: e.target.value})}
  onKeyDown={handleKeyPress}

/>

{/* Show Update+Cancel when editing, else  Add button */}
{editId ? (
  <>
    <button 
      style={{ padding: "8px 12px", margin: "5px", cursor: "pointer", backgroundColor: "green", color: "white" }}
      onClick={update}
    >Update</button>
    <button 
      style={{ padding: "8px 12px", margin: "5px", cursor: "pointer", backgroundColor: "gray", color: "white" }}
      onClick={() => { 
        setEditId(null); 
        setForm({ name: "", age: "", email: "", city: "", occupation: "" }); 
      }}
    >Cancel</button>
  </>
) : (
  <button 
    style={{ padding: "8px 12px", margin: "5px", cursor: "pointer" }}
    onClick={add}
  >Add</button>
)}

  {/* Excel file upload */}
<h3>Upload Excel</h3>

<input 
  type="file" 
  onChange={e => setFile(e.target.files[0])}
/>

<button onClick={upload}>
  Upload
</button>

      {/* Users data table */}
      <table 
  style={{ 
    width: "100%", 
    marginTop: 16, 
    borderCollapse: "collapse" 
  }}
  border="1"
  cellPadding="8"
>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Email</th>
            <th>City</th>
            <th>Occupation</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>

          {/* Loop through users  */}
          {users.map(u => (
            <tr key={u.id}>
              <td>{u.id}</td>
              <td>{u.name}</td>
              <td>{u.age}</td>
              <td>{u.email}</td>
              <td>{u.city}</td>
              <td>{u.occupation}</td>
              <td>
                {/* Edit and Delete buttons for each row */}
                <button 
                  style={{ marginRight: "5px", cursor: "pointer" }}
                  onClick={() => edit(u)}>Edit</button>
                <button onClick={() => del(u.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}