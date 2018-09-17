package com.spstes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spstes.model.Course;
import com.spstes.model.CourseGrade;
import com.spstes.model.CourseInMajor;
import com.spstes.model.Major;
import com.spstes.model.PreparedMajor;
import com.spstes.model.PreparedMajorInTestPlace;
import com.spstes.model.TestPlace;
import com.spstes.model.TestTime;
import com.spstes.service.CourseGradeService;
import com.spstes.service.CourseInMajorService;
import com.spstes.service.CourseService;
import com.spstes.service.MajorService;
import com.spstes.service.PassengerService;
import com.spstes.service.PreparedMajorInTestPlaceService;
import com.spstes.service.TestPlaceService;
import com.spstes.service.TestTimeService;

import net.sf.json.JSONArray;

@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class Admin {
	@Autowired
	TestTimeService testTimeService;
	@Autowired
	CourseInMajorService courseInMajorService;
	@Autowired
	MajorService majorService;
	@Autowired
	CourseService courseService;
	@Autowired
	PassengerService passengerService;
	@Autowired
	CourseGradeService courseGradeService;
	@Autowired
	TestPlaceService testPlaceService;
	@Autowired
	PreparedMajorInTestPlaceService preparedMajorInTestPlaceService;

	@RequestMapping("/index")
	public ModelAndView adminIndex() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/index");
		return model;
	}

	@RequestMapping("/kaocijihua")
	public ModelAndView kaociManagement(HttpServletRequest request, HttpServletResponse response) {
		Integer type = Integer.valueOf(request.getParameter("type"));
		if (type == 0) {
			// 显示图表
			List<TestTime> testTimes = testTimeService.offerAllTestTime();
			ModelAndView model = new ModelAndView();
			model.addObject("testtimes", testTimes);
			model.setViewName("");
			return model;
		} else if (type == 1) {
			// 修改并显示
			Integer examNum = Integer.valueOf(request.getParameter(""));
			String teststarttime = request.getParameter("");
			String testendtime = request.getParameter("");
			String freeze = request.getParameter("");
			String enrollstarttime = request.getParameter("");
			String enrollendtime = request.getParameter("");
			TestTime testTime = new TestTime();
			testTime.setExa_num(examNum);
			testTime.setEstart_time(teststarttime);
			testTime.setEstop_time(testendtime);
			testTime.setFreeze(freeze);
			testTime.setBstart_time(enrollstarttime);
			testTime.setEstop_time(enrollendtime);
			testTimeService.modifyTestTime(testTime);

			List<TestTime> testTimes = testTimeService.offerAllTestTime();
			ModelAndView model = new ModelAndView();
			model.addObject("testtimes", testTimes);
			model.setViewName("");
			return model;
		} else {
			// 增添并显示
			String teststarttime = request.getParameter("");
			String testendtime = request.getParameter("");
			String freeze = request.getParameter("");
			String enrollstarttime = request.getParameter("");
			String enrollendtime = request.getParameter("");
			TestTime testTime = new TestTime();
			testTime.setEstart_time(teststarttime);
			testTime.setEstop_time(testendtime);
			testTime.setFreeze(freeze);
			testTime.setBstart_time(enrollstarttime);
			testTime.setEstop_time(enrollendtime);
			testTimeService.addTestTime(testTime);

			List<TestTime> testTimes = testTimeService.offerAllTestTime();
			ModelAndView model = new ModelAndView();
			model.addObject("testtimes", testTimes);
			model.setViewName("");
			return model;
		}
	}

	@RequestMapping("/biyetiaojian")
	public ModelAndView graguateCondition(HttpServletRequest request, HttpServletResponse response) {
		Integer type = Integer.valueOf(request.getParameter("type"));
		if (type == 0) {
			// 显示
			List<Major> majors = majorService.offerAllMajor();
			ModelAndView model = new ModelAndView();
			model.addObject("majors", majors);
			model.setViewName("admin/biye");
			return model;
		} else if (type == 1) {
			// 答辩与否
			String anser = request.getParameter("dabian");
			String idset = request.getParameter("idset");
			JSONArray jsonArray = JSONArray.fromObject(idset);
			for (int i = 0; i < jsonArray.size(); i++) {
				Integer id = jsonArray.getInt(i);
				majorService.modifyMajorAnser(id, anser);
			}
			return null;
		} else {
			// 设置学分
			Double credit = Double.valueOf(request.getParameter("credit"));
			Integer id = Integer.valueOf(request.getParameter("id"));
			System.out.println(credit);
			System.out.println(id);
			majorService.modifyMajorCredit(id, credit);
			return null;
		}
	}

	@RequestMapping("/kecheng")
	public ModelAndView course(HttpServletRequest request, HttpServletResponse response) {
		Integer type = Integer.valueOf(request.getParameter("type"));
		if (type == 0) {
			// 显示
			List<Course> courses = courseService.offerAllCourse();
			ModelAndView model = new ModelAndView();
			model.addObject("courses", courses);
			model.setViewName("admin/course");
			return model;
		} else if (type == 1) {
			// 搜索并显示
			String searchType = request.getParameter("searchtype");
			List<Course> courses = courseService.offerCourseByType(searchType);
			ModelAndView model = new ModelAndView();
			model.addObject("courses", courses);
			model.setViewName("admin/course");
			return model;
		} else if (type == 2) {
			// 增加
			String course_name = request.getParameter("course_name");
			String course_Ename = request.getParameter("course_Ename");
			Double course_credit = Double.valueOf(request.getParameter("course_credit"));
			String course_chara = request.getParameter("course_chara");
			String exa_method = request.getParameter("exa_method");
			String offer_method = request.getParameter("offer_method");
			Integer bookID = Integer.valueOf(request.getParameter("bookID"));
			String cap_course = request.getParameter("cap_course");
			String used = request.getParameter("used");

			Course course = new Course();
			course.setCourse_name(course_name);
			course.setCourse_Ename(course_Ename);
			course.setCourse_credit(course_credit);
			course.setCourse_chara(course_chara);
			course.setExa_method(exa_method);
			course.setOffer_method(offer_method);
			course.setBookID(bookID);
			course.setCap_course(cap_course);
			course.setUsed(used);
			courseService.addCourses(course);

			return null;
		} else if (type == 3) {
			// 启用与否
			String freeze = request.getParameter("freeze");
			String idset = request.getParameter("idset");
			System.out.println(idset);
			JSONArray jsonArray = JSONArray.fromObject(idset);
			for (int i = 0; i < jsonArray.size(); i++) {
				Integer id = jsonArray.getInt(i);
				courseService.freezeCourseById(id, freeze);
			}
			return null;
		} else {
			// 设置学分
			Double credit = Double.valueOf(request.getParameter("credit"));
			Integer id = Integer.valueOf(request.getParameter("id"));
			courseService.updateCreditById(id, credit);
			return null;
		}
	}

	@RequestMapping("/kaodian")
	public ModelAndView examPoint(HttpServletRequest request, HttpServletResponse response) {
		Integer type = Integer.valueOf(request.getParameter("type"));
		if (type == 0) {
			// 显示
			List<TestPlace> testPlaces = testPlaceService.offerAllTestPlace();
			ModelAndView model = new ModelAndView();
			model.addObject("testplaces", testPlaces);
			model.setViewName("admin/exa_place");
			return model;
		} else if (type == 1) {
			// 启用停用
			String freeze = request.getParameter("freeze");
			String idset = request.getParameter("idset");
			JSONArray jsonArray = JSONArray.fromObject(idset);
			for (int i = 0; i < jsonArray.size(); i++) {
				Integer id = jsonArray.getInt(i);
				testPlaceService.freezeTestPlaceById(id, freeze);
			}
			return null;
		} else {
			// 增加并显示
			String test_name = request.getParameter("test_name");
			String test_addr = request.getParameter("test_addr");
			Integer test_num = Integer.valueOf(request.getParameter("test_num"));
			String linkman_name = request.getParameter("linkman_name");
			String linkman_tel = request.getParameter("linkman_tel");
			String test_use = request.getParameter("test_use");
			TestPlace testPlace = new TestPlace();
			testPlace.setTest_name(test_name);
			testPlace.setTest_addr(test_addr);
			testPlace.setTest_num(test_num);
			testPlace.setLinkman_name(linkman_name);
			testPlace.setLinkman_tel(linkman_tel);
			testPlace.setTest_use(test_use);
			testPlaceService.addTestPlace(testPlace);
			return null;
		}
	}

	@RequestMapping("/kaodiankaikaozhuanye")
	public ModelAndView examStarting(HttpServletRequest request, HttpServletResponse response) {
		Integer type = Integer.valueOf(request.getParameter("type"));
		if (type == 0) {
			// 显示
			List<PreparedMajorInTestPlace> preparedMajorInTestPlaces = preparedMajorInTestPlaceService
					.offerAllMajorInTestPlaceById();
			ModelAndView model = new ModelAndView();
			model.addObject("preparedmajorintestplaces", preparedMajorInTestPlaces);
			model.setViewName("admin/kaikao_major");
			return model;
		} else {
			// 批量删除并显示
			String idset = request.getParameter("idset");
			JSONArray jsonArray = JSONArray.fromObject(idset);
			for (int i = 0; i < jsonArray.size(); i++) {
				Integer id = jsonArray.getInt(i);
				preparedMajorInTestPlaceService.deletePreparedMajorInTestPlaceById(id);
			}
			return null;
		}
	}

	@RequestMapping("/kechengshoufeibiaozhun")
	public ModelAndView examCharge() {
		ModelAndView model = new ModelAndView();
		model.setViewName("");
		return model;
	}

	@RequestMapping("/zhuanyeguanli")
	public ModelAndView ProfessionManagement(HttpServletRequest request, HttpServletResponse response) {
		Integer type = Integer.valueOf(request.getParameter(""));
		if (type == 0) {
			// 进入页面,全局查询
			List<Major> majors = majorService.offerAllMajor();

			ModelAndView model = new ModelAndView();
			model.addObject("majors", majors);
			model.setViewName("");
			return model;
		} else if (type == 1) {
			// 全国查询
			List<Major> majors = majorService.offerMajorByType("是");

			ModelAndView model = new ModelAndView();
			model.addObject("majors", majors);
			model.setViewName("");
			return model;
		} else if (type == 2) {
			// 四川省查询
			List<Major> majors = majorService.offerMajorByType("否");

			ModelAndView model = new ModelAndView();
			model.addObject("majors", majors);
			model.setViewName("");
			return model;
		} else if (type == 3) {
			// 开考专业查询
			List<PreparedMajor> preparedMajors = passengerService.searchProfession();

			ModelAndView model = new ModelAndView();
			model.addObject("preparedmajors", preparedMajors);
			model.setViewName("");
			return model;
		} else if (type == 4) {
			// 新增课程
			String customId = request.getParameter("");
			String majorName = request.getParameter("");
			double majorCredit = Double.valueOf(request.getParameter(""));
			String used = request.getParameter("");
			String examine = request.getParameter("");
			String examinetime = request.getParameter("");
			String exaType = request.getParameter("");
			String codeType = request.getParameter("");
			String technicality = request.getParameter("");
			String capMajor = request.getParameter("");
			Integer uniformExa = Integer.valueOf(request.getParameter(""));
			String school = request.getParameter("");
			String proClass = request.getParameter("");
			String profLevel = request.getParameter("");
			String ProfType = request.getParameter("");
			String anser = request.getParameter("");
			Major major = new Major();
			major.setCustomID(customId);
			major.setMajor_name(majorName);
			major.setMajor_credit(majorCredit);
			major.setUsed(used);
			major.setExamine(examine);
			major.setExaminetime(examinetime);
			major.setExa_type(exaType);
			major.setCode_type(codeType);
			major.setTechnicality(technicality);
			major.setCap_major(capMajor);
			major.setUniform_exa(uniformExa);
			major.setSchool(school);
			major.setPro_class(proClass);
			major.setPorf_level(profLevel);
			major.setPorf_type(ProfType);
			major.setAnser(anser);
			majorService.addMajor(major);

			List<Major> majors = majorService.offerAllMajor();
			ModelAndView model = new ModelAndView();
			model.addObject("majors", majors);
			model.setViewName("");
			return model;
		} else {
			// 启用停用
			String used = request.getParameter("");
			Integer majorId = Integer.valueOf(request.getParameter(""));
			majorService.freezeMajorById(majorId, used);

			List<Major> majors = majorService.offerAllMajor();
			ModelAndView model = new ModelAndView();
			model.addObject("majors", majors);
			model.setViewName("");
			return model;
		}
	}

	@RequestMapping("/kaoshengchengji")
	public ModelAndView studentMarks(HttpServletRequest request, HttpServletResponse response) {
		Integer type = Integer.valueOf(request.getParameter(""));
		if (type == 0) {
			// 显示
			List<CourseGrade> courseGrades = courseGradeService.offerAllCourses();
			ModelAndView model = new ModelAndView();
			model.addObject("courseGrades", courseGrades);
			return model;
		} else {
			// 修改并显示
			Double grade = Double.valueOf(request.getParameter(""));
			Integer id = Integer.valueOf(request.getParameter(""));
			courseGradeService.modifyCourseGradeById(id, grade);

			List<CourseGrade> courseGrades = courseGradeService.offerAllCourses();
			ModelAndView model = new ModelAndView();
			model.addObject("courseGrades", courseGrades);
			return model;
		}
	}

	@RequestMapping("/zhuanyekecheng")
	public ModelAndView ProfessionCourse(HttpServletRequest request, HttpServletResponse response) {
		Integer type = Integer.valueOf(request.getParameter(""));
		if (type == 0) {
			// 查询并显示
			Integer searchid = Integer.valueOf(request.getParameter(""));
			List<CourseInMajor> form = courseInMajorService.offerCourseInMajorById(searchid);
			List<Major> select = majorService.offerAllMajor();
			ModelAndView model = new ModelAndView();
			model.addObject("form", form);
			model.addObject("select", select);
			model.setViewName("");
			return model;
		} else if (type == 1) {
			// 点击增加自动填充表单
			List<Major> majors = majorService.offerAllMajor();
			List<Course> courses = courseService.offerAllCourse();
			ModelAndView model = new ModelAndView();
			model.addObject("majors", majors);
			model.addObject("courses", courses);
			model.setViewName("");
			return model;
		} else if (type == 2) {
			// 点击增加完成添加并显示
			Integer majorId = Integer.valueOf(request.getParameter(""));
			Integer courseId = Integer.valueOf(request.getParameter(""));
			String courseType = request.getParameter("");
			CourseInMajor courseInMajor = new CourseInMajor();
			courseInMajor.setMajorID(majorId);
			courseInMajor.setCourseID(courseId);
			courseInMajor.setCourse_type(courseType);
			courseInMajorService.addCourse(courseInMajor);

			Integer searchid = Integer.valueOf(request.getParameter(""));
			List<CourseInMajor> form = courseInMajorService.offerCourseInMajorById(searchid);
			List<Major> select = majorService.offerAllMajor();
			ModelAndView model = new ModelAndView();
			model.addObject("form", form);
			model.addObject("select", select);
			model.setViewName("");
			return model;

		} else if (type == 3) {
			// 完成修改并显示
			Integer courseTypeId = Integer.valueOf(request.getParameter(""));
			Integer majorId = Integer.valueOf(request.getParameter(""));
			Integer courseId = Integer.valueOf(request.getParameter(""));
			String courseType = request.getParameter("");
			CourseInMajor courseInMajor = new CourseInMajor();
			courseInMajor.setCouritypeID(courseTypeId);
			courseInMajor.setMajorID(majorId);
			courseInMajor.setCourseID(courseId);
			courseInMajor.setCourse_type(courseType);
			courseInMajorService.modifyCourse(courseInMajor);

			Integer searchid = Integer.valueOf(request.getParameter(""));
			List<CourseInMajor> form = courseInMajorService.offerCourseInMajorById(searchid);
			List<Major> select = majorService.offerAllMajor();
			ModelAndView model = new ModelAndView();
			model.addObject("form", form);
			model.addObject("select", select);
			model.setViewName("");
			return model;
		} else {
			// 完成批量删除并显示
			String deleteIdSetJsonStr = request.getParameter("");
			JSONArray deleteIdSetJson = JSONArray.fromObject(deleteIdSetJsonStr);
			for (int i = 0; i < deleteIdSetJson.size(); i++) {
				Integer id = (Integer) deleteIdSetJson.get(i);
				courseInMajorService.deleteCourse(id);
			}

			Integer searchid = Integer.valueOf(request.getParameter(""));
			List<CourseInMajor> form = courseInMajorService.offerCourseInMajorById(searchid);
			List<Major> select = majorService.offerAllMajor();
			ModelAndView model = new ModelAndView();
			model.addObject("form", form);
			model.addObject("select", select);
			model.setViewName("");
			return model;
		}
	}
}
