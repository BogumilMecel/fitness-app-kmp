# Coding Rules for Fitness App KMP

## General Rules

1. **User Changes Priority**: If I do something and the user changes it, don't change it back or at least ask before changing it
2. **No Comments**: Do not put any comments unless explicitly asked
3. **Error Handling**: Do not use Resource, use `runCatching` or `runCatchingWithSnackbarOnFailure` for requests (example is in DiarySearchScreenModel)

## Screen Development

1. **New Screen Creation**: When asked to add new screen, always add it without any code, just with constructor
2. **Screen Parameters**: Each screen should only take `State` and `onEvent` callback that are passed in App.kt

## Serialization

1. **SerialName Annotations**: All enums should have `@SerialName` annotations with lowercase values
2. **Enum Formatting**: 
   - One line break between each enum entry
   - Each enum in separate file when possible
   - Use snake_case for multi-word SerialName values (e.g., "very_high", "type_of_work")