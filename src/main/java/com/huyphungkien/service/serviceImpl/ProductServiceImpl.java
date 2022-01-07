package com.huyphungkien.service.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.huyphungkien.domain.Category;
import com.huyphungkien.domain.Image;
import com.huyphungkien.domain.TypePro;
import com.huyphungkien.model.ProductDto;
import com.huyphungkien.model.TypeProDto;
import com.huyphungkien.repository.*;
import com.huyphungkien.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.huyphungkien.domain.Product;
import com.huyphungkien.service.ProductService;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository pr;
	@Autowired
	CategoryReposity cr;
	@Autowired
	SizeRepository sr;
	@Autowired
	OrderDetailRepository odr;
	@Autowired
	StorageService ss;
	@Autowired
	EntityManager manage;
	@Autowired
	ImageRepository ir;
	@Override
	public ProductDto save(ProductDto dto){
		if(dto!=null){
			Product product=null;
			if(dto.getProductId()!=null)
			{
				product=pr.getOne(dto.getProductId());
				product.setProductId(dto.getProductId());
			}
			if(product==null){
				product=new Product();
			}
			product.setName(dto.getName());
			product.setQuantity(dto.getQuantity());
			product.setUnitPrice(dto.getUnitPrice());
			product.setDescription(dto.getDescription());
			product.setDiscount(dto.getDiscount());
			product.setEnteredDate(dto.getEnteredDate());
			product.setStatus(dto.getStatus());
			if(dto.getCategory()!=null&&dto.getCategory().getCategoryId()!=null){
				Category category=cr.getOne(dto.getCategory().getCategoryId());
				product.setCategory(category);
			}
			product=pr.save(product);
			if(dto.getFiles()!=null&&dto.getFiles().length>0)
			{
				List<Image> images=new ArrayList<Image>();
				for (MultipartFile m: dto.getFiles()) {
					if(m!=null&&!m.isEmpty())
					{
						Image image=new Image();
						image.setId(5L);
						UUID uuid=UUID.randomUUID();
						image.setImg(ss.getStorageFileName(m, uuid.toString()));
						ss.store(m,image.getImg());
						image.setProduct(product);
						ir.save(image);

					}
				}
			}
			if(dto.getSizes()!=null&&dto.getSizes().size()>0)
			{
				for (TypeProDto sizeDto:dto.getSizes()) {
					if(sizeDto.getName()!=null&&!sizeDto.getName().isEmpty()){
						TypePro size=null;
						if(sizeDto.getSizeId()!=null)
						{
							size=sr.getOne(sizeDto.getSizeId());
						}
						if (size==null){
							size=new TypePro();
						}
						size.setName(sizeDto.getName());
						size.setQuantity(sizeDto.getQuantity());
						if(product!=null){
							size.setProduct(product);
						}
						size=sr.save(size);
					}
				}
			}
			if(product!=null){
				return new ProductDto(product,true);
			}
		}
		return null;
	}

	@Override
	public Optional<Product> findById(Long id) {
		return pr.findById(id);
	}

	@Override
	public Page<Product> findAll(Pageable page) {
		return null;
	}

	@Override
	public void deleteById(Long aLong) throws IOException {
		Product p=pr.getById(aLong);
		if(p.getImages()!=null&&p.getImages().size()>0){
			for (Image i:p.getImages()) {
				ss.delete(i.getImg());
			}
		}
		pr.deleteById(aLong);
	}
	@Override
	public Product getOne(Long id) {
		return pr.getOne(id);
	}
	@Override
	public Page<ProductDto> searchByPage(String key,Long id){
		String whereClause="";
		String sql="select new com.huyphungkien.model.ProductDto(p,true) from Product p ";
		String sqlCount="select count(p.id) from Product p ";
		String[] a=key.trim().split(" ");
		if(!key.isEmpty()){
			whereClause+="where ( p.name LIKE :key";
			for (int i=1;i<a.length;i++) {
				whereClause+=" or p.name LIKE :key"+i;
			}
		}
		whereClause+=")";
		if(id!=null){
			whereClause+=" and p.productId != :id";
		}
		sql+=whereClause;
		sqlCount+=whereClause;
		javax.persistence.Query q=manage.createQuery(sql,ProductDto.class);
		javax.persistence.Query qCount=manage.createQuery(sqlCount);
		if(!key.isEmpty())
		{
			q.setParameter("key","%"+key+"%");
			qCount.setParameter("key","%"+key+"%");
			for (int i=1;i<a.length;i++) {
				q.setParameter("key"+i,"%"+a[i]+"%");
				qCount.setParameter("key"+i,"%"+a[i]+"%");
			}


		}if(id!=null){
			q.setParameter("id",id);
			qCount.setParameter("id",id);
		}
		q.setMaxResults(10);
		q.setFirstResult(0);
		long c= (long) qCount.getSingleResult();
		Pageable page= PageRequest.of(0,10);
		return new PageImpl<ProductDto>(q.getResultList(),page,c);

	}


	@Override
	public Product getById(Long id) {
		return pr.getById(id);
	}

	@Override
	@Query("select new com.huyphungkien.model.ProductDto(p) from Product p")
	public Page<ProductDto> findAllPro(Pageable page) {
		return pr.findAllPro(page);
	}
	@Override
	public List<ProductDto> getProductHot(int status){
		String sql="select new com.huyphungkien.model.ProductDto(p,true) from Product p where p.status= :status";
		javax.persistence.Query q=manage.createQuery(sql,ProductDto.class);
		q.setParameter("status",status);
		return q.getResultList();
	}


}
