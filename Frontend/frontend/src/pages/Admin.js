import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function Admin() {
    const [users, setUsers] = useState([]);
    const [newUser, setNewUser] = useState({ username: "", password: "" });

    // Fetch users from the database
    useEffect(() => {
        fetch("https://cst438-project2-33edc6317781.herokuapp.com/user/all") 
            .then((response) => response.json())
            .then((data) => setUsers(data))
            .catch((error) => console.error("Error fetching users:", error));
    }, []);

    // Handles changes in input fields for existing users
    const handleEdit = (id, field, value) => {
        setUsers(users.map(user =>
            user.id === id ? { ...user, [field]: value } : user
        ));
    };

    // Save updated user data
    const handleSave = (user) => {
        fetch(`https://cst438-project2-33edc6317781.herokuapp.com/user/editUser/${user.id}`, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username: user.username,
                password: user.password,
            }),
        })
        .then(response => response.json())
        .then(updatedUser => {
            console.log("User updated successfully:", updatedUser);
        })
        .catch(error => console.error("Error updating user:", error));
    };

    // Delete user from database
    const handleDelete = (id) => {
        fetch(`https://cst438-project2-33edc6317781.herokuapp.com/user/deleteUser/${id}`, {
            method: "DELETE",
        })
        .then(response => response.json())
        .then(success => {
            if (success) {
                setUsers(users.filter(user => user.id !== id));
                console.log("User deleted successfully");
            } else {
                console.error("Error deleting user");
            }
        })
        .catch(error => console.error("Error deleting user:", error));
    };

    // Handle input changes for new user form
    const handleNewUserChange = (field, value) => {
        setNewUser(prev => ({ ...prev, [field]: value }));
    };

    // Add a new user to the database
    const handleAddUser = () => {
        if (!newUser.username || !newUser.password) {
            alert("Username and password are required!");
            return;
        }

        fetch("https://cst438-project2-33edc6317781.herokuapp.com/user/addUser", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newUser),
        })
        .then(response => response.json())
        .then(addedUser => {
            console.log("User added successfully:", addedUser);
            setUsers([...users, addedUser]); 
            setNewUser({ username: "", password: "" });  
        })
        .catch(error => console.error("Error adding user:", error));
    };

    return (
        <div>
            <Link to="/"> Home Page</Link>
            <Link to="/Admin"> Admin Page</Link>
            <Link to="/Login"> Login Page</Link>
            <Link to="/Profile"> Profile Page</Link>
            <Link to="/Signup"> Signup Page</Link>
            <Link to="/Tierlist"> TierList Page</Link>

            <h1>Admin Page</h1>
            <p>Manage users below:</p>

            {/* Add User Form */}
            <h2>Add User</h2>
            <input
                type="text"
                placeholder="Username"
                value={newUser.username}
                onChange={(e) => handleNewUserChange("username", e.target.value)}
            />
            <input
                type="password"
                placeholder="Password"
                value={newUser.password}
                onChange={(e) => handleNewUserChange("password", e.target.value)}
            />
            <button onClick={handleAddUser}>Add User</button>

            {users.length === 0 ? <p>Loading users...</p> : (
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {users.map((user) => (
                            <tr key={user.id}>
                                <td>{user.id}</td>
                                <td>
                                    <input
                                        type="text"
                                        value={user.username}
                                        onChange={(e) => handleEdit(user.id, "username", e.target.value)}
                                    />
                                </td>
                                <td>
                                    <input
                                        type="password"
                                        value={user.password}
                                        onChange={(e) => handleEdit(user.id, "password", e.target.value)}
                                    />
                                </td>
                                <td>
                                    <button onClick={() => handleSave(user)}>Save</button>
                                    <button onClick={() => handleDelete(user.id)} 
                                            style={{ marginLeft: "10px", backgroundColor: "red", color: "white" }}>
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}
