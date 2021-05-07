import { Observable , of, throwError} from 'rxjs';
import { retryWhen , delay, mergeMap} from 'rxjs/operators';

export function delayedRetry(delayMs: number, maxRetry: number){
    let retries = maxRetry;

    return (src: Observable<any> ) => 
    src.pipe(
        retryWhen( (errors: Observable<any> ) => errors.pipe(
            delay(delayMs),
            mergeMap(error => retries-- > 0 ? of(error) : throwError(maxRetry)
            ))
        )
    );
}