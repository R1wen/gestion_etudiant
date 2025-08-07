//Pour afficher les étudiants
document.addEventListener("DOMContentLoaded", function () {
  const apiUrl = "http://localhost:8080/student";
  const tableBody = document.getElementById("tableBody");

  fetch(apiUrl)
    .then((response) => response.json())
    .then((data) => {
      data.forEach((student) => {
        const row = document.createElement("tr");
        row.innerHTML = `
                <td>${student.id}</td>
                <td>${student.nom}</td> 
                <td>${student.prenom}</td> 
                <td>${student.dateNaissance}</td>
                <td><a href="notes.html?studentId=${student.id}">Voir</a></td>
                <td><a href="#" class="modifier-link">Modifier</a></td>
                <td><a href="#" class="supprimer-link">Supprimer</a></td>
                `;
                
        row
          .querySelector(".supprimer-link")
          .addEventListener("click", function (e) {
            e.preventDefault();
            fetch(`http://localhost:8080/student/${student.id}`, {
              method: "DELETE",
            }).then((response) => {
              if (response.ok) {
                alert("Etudiant supprimé avec succès!");
                row.remove();
              } else {
                alert("Erreur lors de la suppression");
              }
            });
          });

        row
          .querySelector(".modifier-link")
          .addEventListener("click", function (e) {
            e.preventDefault();
            const nouveauNom = prompt("Nouveau nom :", student.nom);
            const nouveauPrenom = prompt("Nouveau prénom :", student.prenom);
            const nouvelleDateNaissance = prompt(
              "Nouvelle date de naissance :",
              student.dateNaissance
            );

            if (nouveauNom && nouveauPrenom && nouvelleDateNaissance) {
              fetch(`http://localhost:8080/student/${student.id}`, {
                method: "PUT",
                headers: {
                  "Content-Type": "application/json",
                },
                body: JSON.stringify({
                  nom: nouveauNom,
                  prenom: nouveauPrenom,
                  dateNaissance: nouvelleDateNaissance,
                }),
              }).then((response) => {
                if (response.ok) {
                  window.location.reload();
                } else {
                  alert("Erreur lors de la modification");
                }
              });
            }
          });
        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      console.log("error is comming while api calling " + error);
    });

  const form = document.getElementById("studentForm");
  form.addEventListener("submit", function (event) {
    event.preventDefault();
    const data = {
      nom: form.nom.value,
      prenom: form.prenom.value,
      dateNaissance: form.dateNaissance.value,
    };

    fetch("http://localhost:8080/student", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((response) => {
        if (!response.ok)
          throw new Error("Erreur lors de la création de l'étudiant.");
        return response.json();
      })
      .then(() => {
        form.reset();
        window.location.reload(); // Pour rafraîchir la liste après ajout
      })
      .catch((error) => {
        console.error(error);
      });
  });
});
