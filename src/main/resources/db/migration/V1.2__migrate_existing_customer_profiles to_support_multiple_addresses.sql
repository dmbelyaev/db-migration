UPDATE address SET main = TRUE, profile_id = (SELECT id FROM customer_profile WHERE customer_profile.address_id = address.id);
