# Discriminator approach

The discriminator approach is about using the same database and separate the data using a column inside the entity/table. We can implement two ways.

## AOP solution
We can use an AOP solution, implemented in `TentantAspect` class, before executing any method on Repositories. **In this project, this solution is disabled**.

### Considerations 
If the `open-in-view` is set as false, we need `@Transactional` on our service methods to work

## Transaction Manager Solution
The other solution is adding a customized Transaction manager and overriding `createEntityManagerForTransaction` method to enable filter.

### Considerations
The `open-in-view` must set as false. `@Transactional` has no effect to the behavior.