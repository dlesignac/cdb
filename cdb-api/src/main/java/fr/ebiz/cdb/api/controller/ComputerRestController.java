package fr.ebiz.cdb.api.controller;

import fr.ebiz.cdb.api.exception.InvalidParameterException;
import fr.ebiz.cdb.binding.ComputerDTO;
import fr.ebiz.cdb.binding.PageRequest;
import fr.ebiz.cdb.binding.error.Error;
import fr.ebiz.cdb.binding.error.Errors;
import fr.ebiz.cdb.core.Page;
import fr.ebiz.cdb.service.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/computers")
public class ComputerRestController {

    @Autowired
    private IComputerService computerService;

    /**
     * Get computer page.
     *
     * @param pageRequest   pageRequest
     * @param bindingResult bindingResult
     * @return Page<ComputerDTO>
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<ComputerDTO> fetch(@Valid PageRequest pageRequest, BindingResult bindingResult) {
        manageErrors(bindingResult);
        return computerService.page(pageRequest);
    }

    /**
     * Create computer.
     *
     * @param computerDTO   computerDTO
     * @param bindingResult bindingResult
     * @return ComputerDTO
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ComputerDTO create(@RequestBody @Valid ComputerDTO computerDTO, BindingResult bindingResult) {
        manageErrors(bindingResult);
        computerService.create(computerDTO);
        return computerDTO;
    }

    /**
     * Get computer by id.
     *
     * @param id id
     * @return ComputerDTO
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ComputerDTO read(@PathVariable int id) {
        ComputerDTO computerDTO = computerService.find(id);

        if (computerDTO == null) {
            throw new NoSuchElementException();
        }

        return computerDTO;
    }

    /**
     * Update computer.
     *
     * @param computerDTO   ComputerDTO
     * @param bindingResult bindingResult
     * @return ComputerDTO
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ComputerDTO update(@PathVariable int id, @RequestBody @Valid ComputerDTO computerDTO, BindingResult bindingResult) {
        manageErrors(bindingResult);
        computerDTO.setId(id);

        if (computerService.find(id) == null) {
            throw new NoSuchElementException();
        }

        computerService.update(computerDTO);
        return computerDTO;
    }

    /**
     * Delete computer by id.
     *
     * @param id id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        if (computerService.delete(id) == 0) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Manage errors.
     *
     * @param bindingResult bindingResult
     */
    private void manageErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            List<Error> list = new ArrayList<>();

            for (ObjectError o : bindingResult.getAllErrors()) {
                Error error = new Error();
                error.setCause(((FieldError) o).getField());
                error.setMessage(o.getDefaultMessage());
                list.add(error);
            }

            Errors errors = new Errors();
            errors.setErrors(list);

            throw new InvalidParameterException(errors);
        }
    }

}
