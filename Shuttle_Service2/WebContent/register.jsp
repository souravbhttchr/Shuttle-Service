<h1>Registration Page</h1><br><br>

<form action="register.sb" method="post">
	<table>
		<tr>
			<td>
				First Name : 
			</td>
			<td>
				<input type="text" name="firstName">
			</td>		
		</tr>
		
		<tr>
			<td>
				Last Name : 
			</td>
			<td>
				<input type="text" name="lastName">
			</td>		
		</tr>
	
		<tr>
			<td>
				Email : 
			</td>
			<td>
				<input type="email" name="email">
			</td>		
		</tr>
		
		<tr>
			<td>
				Password : 
			</td>
			<td>
				<input type="password" name="password">
			</td>		
		</tr>
		
		<tr>
			<td>
				Question : 
			</td>
			<td>
				<select name="question">
					<option name="blank" value="blank">---Select---</option>
					<option name="option1" value="Pet Name">What is your pet name?</option>
					<option name="option2" value="Mother's Maiden Name">What was your mother's maiden name?</option>
				</select>
			</td>		
		</tr>
		
		<tr>
			<td>
				Answer : 
			</td>
			<td>
				<input type="text" name="answer">
			</td>		
		</tr>
	</table>
	
	<br><br>
	
	<input type="submit" value="Register">

</form>