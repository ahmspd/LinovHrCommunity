package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.CategoryDao;
import com.lawencon.linovhrcommunity.dto.category.DeleteByIdCategoryDtoDataRes;
import com.lawencon.linovhrcommunity.dto.category.DeleteMultipleCategoryDtoDataReq;
import com.lawencon.linovhrcommunity.dto.category.DeleteMultipleCategoryDtoReq;
import com.lawencon.linovhrcommunity.dto.category.DeleteMultipleCategoryDtoRes;
import com.lawencon.linovhrcommunity.dto.category.GetAllCategoryDtoDataRes;
import com.lawencon.linovhrcommunity.dto.category.GetAllCategoryDtoRes;
import com.lawencon.linovhrcommunity.dto.category.GetAllCategoryPageDtoDataRes;
import com.lawencon.linovhrcommunity.dto.category.GetAllCategoryPageDtoRes;
import com.lawencon.linovhrcommunity.dto.category.GetByIdCategoryDtoDataRes;
import com.lawencon.linovhrcommunity.dto.category.GetByIdCategoryDtoRes;
import com.lawencon.linovhrcommunity.dto.category.InsertCategoryDtoDataRes;
import com.lawencon.linovhrcommunity.dto.category.InsertCategoryDtoReq;
import com.lawencon.linovhrcommunity.dto.category.InsertCategoryDtoRes;
import com.lawencon.linovhrcommunity.dto.category.UpdateCategoryDtoDataRes;
import com.lawencon.linovhrcommunity.dto.category.UpdateCategoryDtoReq;
import com.lawencon.linovhrcommunity.dto.category.UpdateCategoryDtoRes;
import com.lawencon.linovhrcommunity.model.Category;

@Service
public class CategoryService extends BaseServiceLinovCommunityImpl{
	private CategoryDao cateogryDao;

	@Autowired
	public CategoryService(CategoryDao cateogryDao) {
		this.cateogryDao = cateogryDao;
	}

	public InsertCategoryDtoRes insert(InsertCategoryDtoReq dataReq) throws Exception {
		Category addCategory = new Category();
		addCategory.setCategoryName(dataReq.getCategoryName());
		addCategory.setCode(dataReq.getCode());
		addCategory.setCreatedBy(getIdFromPrincipal());

		try {
			begin();
			valBkNotExist(dataReq.getCode());
			valBkNotNull(dataReq.getCode());
			addCategory = cateogryDao.save(addCategory);
			commit();
			
			InsertCategoryDtoDataRes data = new InsertCategoryDtoDataRes();
			data.setId(addCategory.getId());

			InsertCategoryDtoRes dataRes = new InsertCategoryDtoRes();
			dataRes.setData(data);
			
			dataRes.setMessage(stringBuilder("Insert ", addCategory.getCode(), " Success !"));
			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public UpdateCategoryDtoRes update(UpdateCategoryDtoReq dataReq) throws Exception {
		Category updateCategory = cateogryDao.getById(dataReq.getId());
		updateCategory.setCategoryName(dataReq.getCategoryName());
		updateCategory.setCode(dataReq.getCode());
		updateCategory.setUpdatedBy(getIdFromPrincipal());
		updateCategory.setVersion(dataReq.getVersion());

		try {
			begin();
			valIdNotNull(dataReq.getId());
			valBkNotNull(dataReq.getCode());
			valIdExist(dataReq.getId());
			updateCategory = cateogryDao.save(updateCategory);
			commit();
			
			UpdateCategoryDtoDataRes data = new UpdateCategoryDtoDataRes();
			data.setVersion(updateCategory.getVersion());

			UpdateCategoryDtoRes dataRes = new UpdateCategoryDtoRes();
			dataRes.setData(data);
			dataRes.setMessage(stringBuilder("Update ", updateCategory.getCode(), " Success !"));
			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public GetAllCategoryDtoRes findAll() throws Exception {
		List<GetAllCategoryDtoDataRes> datas = new ArrayList<GetAllCategoryDtoDataRes>();
		List<Category> Categorys = cateogryDao.getAll();
		
		Categorys.forEach(Category -> {
			GetAllCategoryDtoDataRes data = new GetAllCategoryDtoDataRes();
			data.setId(Category.getId());
			data.setCategoryName(Category.getCategoryName());
			data.setCode(Category.getCode());
			data.setIsActive(Category.getIsActive());
			data.setVersion(Category.getVersion());
			datas.add(data);
		});

		GetAllCategoryDtoRes dataRes = new GetAllCategoryDtoRes();
		dataRes.setData(datas);
		return dataRes;
	}
	
	public GetAllCategoryPageDtoRes getAllWithPage(int startPage, int maxPage) throws Exception {
		Long total = cateogryDao.countAll();
		List<GetAllCategoryPageDtoDataRes> datas = new ArrayList<GetAllCategoryPageDtoDataRes>();
		List<Category> Categorys = cateogryDao.getAll(startPage, maxPage);

		Categorys.forEach(Category -> {
			GetAllCategoryPageDtoDataRes data = new GetAllCategoryPageDtoDataRes();
			data.setId(Category.getId());
			data.setCategoryName(Category.getCategoryName());
			data.setCode(Category.getCode());
			data.setIsActive(Category.getIsActive());
			data.setVersion(Category.getVersion());
			datas.add(data);
		});

		GetAllCategoryPageDtoRes dataRes = new GetAllCategoryPageDtoRes();
		dataRes.setData(datas);
		dataRes.setTotal(total);
		
		return dataRes;
	}

	public GetByIdCategoryDtoRes findById(String id) throws Exception {
		Category getCategory = cateogryDao.getById(id);

		GetByIdCategoryDtoDataRes data = new GetByIdCategoryDtoDataRes();
		data.setId(getCategory.getId());
		data.setCategoryName(getCategory.getCategoryName());
		data.setCode(getCategory.getCode());
		data.setIsActive(getCategory.getIsActive());
		data.setVersion(getCategory.getVersion());

		GetByIdCategoryDtoRes dataRes = new GetByIdCategoryDtoRes();
		dataRes.setData(data);
		return dataRes;
	}

	public DeleteByIdCategoryDtoDataRes deleteById(String id) throws Exception {
		DeleteByIdCategoryDtoDataRes dataRes = new DeleteByIdCategoryDtoDataRes();
		try {
			begin();
			valIdExist(id);
			boolean isDeleted = cateogryDao.deleteById(id);
			commit();

			if (isDeleted) {
				dataRes.setMessage("Delete Success");
			} else {
				throw new Exception("Delete Failed");
			}

			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public DeleteMultipleCategoryDtoRes deleteMultiple(DeleteMultipleCategoryDtoReq data) throws Exception {
		DeleteMultipleCategoryDtoRes dataRes = new DeleteMultipleCategoryDtoRes();
		boolean isDeleted = false;
		try {
			begin();
			List<DeleteMultipleCategoryDtoDataReq> dataReq = data.getData();
			for(int i=0; i<dataReq.size();i++) {
				valIdExist(dataReq.get(i).getId());
				isDeleted = cateogryDao.deleteById(dataReq.get(i).getId());
			}

			if (isDeleted) {
				dataRes.setMessage("Delete Success");
			} else {
				throw new Exception("Delete Failed");
			}
			commit();

			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	private void valBkNotExist(String code) {
		Integer res = cateogryDao.isCategoryCodeExist(code);
		if(res == 1) {
			throw new RuntimeException("Category Code Exist");
		}
	}
	
	private void valIdExist(String id) {
		Integer res = cateogryDao.isCategoryIdExist(id);
		if(res == 0) {
			throw new RuntimeException("Category Id Not Exist");
		}
	}
	private void valIdNotNull(String id) {
		Integer res = cateogryDao.isCategoryIdExist(id);
		if(res == 0) {
			throw new RuntimeException("Category Id Not Exist");
		}
	}
	private void valBkNotNull(String code) {
		if(code==null) {
			throw new RuntimeException("Category Code Is Null");
		}
	}
}
