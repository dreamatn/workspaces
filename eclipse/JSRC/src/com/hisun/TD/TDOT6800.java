package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT6800 {
    DBParm TDTGPMP_RD;
    brParm TDTGPMP_BR = new brParm();
    DBParm TDTSMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT1 = "TD670";
    String K_OUTPUT_FMT2 = "TD680";
    String K_OUTPUT_FMT3 = "TD681";
    String WS_MSGID = " ";
    int WS_L_CNT = 0;
    int WS_Q_CNT = 0;
    int WS_P_ROW = 0;
    int WS_NUM = 0;
    int WS_T_PAGE = 0;
    int WS_L_ROW = 0;
    int WS_L_NUM = 0;
    int WS_CNT = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    char WS_ACCU_CLS = ' ';
    TDOT6800_WS_OUTPUT_DATA WS_OUTPUT_DATA = new TDOT6800_WS_OUTPUT_DATA();
    char WS_ACCU_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    SCCMPAG SCCMPAG = new SCCMPAG();
    TDCTZZC TDCTZZC = new TDCTZZC();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICACCU CICACCU = new CICACCU();
    TDCSFRZ TDCSFRZ = new TDCSFRZ();
    TDRGPMP TDRGPMP = new TDRGPMP();
    TDRGRGP TDRGRGP = new TDRGRGP();
    TDRSMST TDRSMST = new TDRSMST();
    SCCGWA SCCGWA;
    TDB6800_AWA_6800 TDB6800_AWA_6800;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDOT6800 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB6800_AWA_6800>");
        TDB6800_AWA_6800 = (TDB6800_AWA_6800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (TDB6800_AWA_6800.FUNC == 'B') {
            B020_BRWSORE_PROC();
            if (pgmRtn) return;
        } else if (TDB6800_AWA_6800.FUNC == 'I') {
            B020_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (TDB6800_AWA_6800.FUNC == 'A') {
            B020_ADD_PROC();
            if (pgmRtn) return;
        } else if (TDB6800_AWA_6800.FUNC == 'M') {
            B020_CHANGE_PROC();
            if (pgmRtn) return;
        } else if (TDB6800_AWA_6800.FUNC == 'D') {
            B020_DEL_PROC();
            if (pgmRtn) return;
    }
    public void B020_BRWSORE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGPMP);
        CEP.TRC(SCCGWA, TDB6800_AWA_6800.PROVD_NO);
        CEP.TRC(SCCGWA, TDB6800_AWA_6800.PRD_NO);
        if (TDB6800_AWA_6800.PROVD_NO.trim().length() > 0 
            && TDB6800_AWA_6800.PRD_NO.trim().length() > 0) {
            TDRGPMP.KEY.PROVD_NO = TDB6800_AWA_6800.PROVD_NO;
            TDRGPMP.KEY.PRD_NO = TDB6800_AWA_6800.PRD_NO;
            T000_STARTBR_ALL();
            if (pgmRtn) return;
        }
        if (TDB6800_AWA_6800.PROVD_NO.trim().length() == 0 
            && TDB6800_AWA_6800.PRD_NO.trim().length() > 0) {
            TDRGPMP.KEY.PRD_NO = TDB6800_AWA_6800.PRD_NO;
            T000_STARTBR_PRD();
            if (pgmRtn) return;
        }
        if (TDB6800_AWA_6800.PROVD_NO.trim().length() > 0 
            && TDB6800_AWA_6800.PRD_NO.trim().length() == 0) {
            TDRGPMP.KEY.PROVD_NO = TDB6800_AWA_6800.PROVD_NO;
            T000_STARTBR_PROVD();
            if (pgmRtn) return;
        }
        if (TDB6800_AWA_6800.PROVD_NO.trim().length() == 0 
            && TDB6800_AWA_6800.PRD_NO.trim().length() == 0) {
            T000_STARTBR();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 444;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        T000_READ_NEXT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, WS_OUTPUT_DATA);
            WS_OUTPUT_DATA.WS_PROVD_NO = TDRGPMP.KEY.PROVD_NO;
            WS_OUTPUT_DATA.WS_PRD_NO = TDRGPMP.KEY.PRD_NO;
            WS_OUTPUT_DATA.WS_PROVD_NM = TDRGPMP.PROVD_NM;
            WS_OUTPUT_DATA.WS_PRD_NM = TDRGPMP.PRD_NM;
            WS_OUTPUT_DATA.WS_AMT = TDRGPMP.AMT;
            WS_OUTPUT_DATA.WS_PROD_CD = TDRGPMP.PROD_CD;
            WS_OUTPUT_DATA.WS_TERM = TDRGPMP.TERM;
            WS_OUTPUT_DATA.WS_PRICE = TDRGPMP.PRICE;
            WS_OUTPUT_DATA.WS_OLD_PRICE = TDRGPMP.OLD_PRICE;
            WS_OUTPUT_DATA.WS_INT_AMT = TDRGPMP.INT_AMT;
            WS_OUTPUT_DATA.WS_DIS_RT = TDRGPMP.DIS_RT;
            WS_OUTPUT_DATA.WS_DIS_INT = TDRGPMP.DIS_INT;
            WS_OUTPUT_DATA.WS_SALE_FEE = TDRGPMP.SALE_FEE;
            WS_OUTPUT_DATA.WS_INT_RATE = TDRGPMP.INT_RATE;
            WS_OUTPUT_DATA.WS_REV_AC = TDRGPMP.REV_AC;
            WS_OUTPUT_DATA.WS_VAL_FGL = TDRGPMP.VAL_FGL;
            WS_OUTPUT_DATA.WS_VAL_DT = TDRGPMP.VAL_DT;
            WS_OUTPUT_DATA.WS_END_DT = TDRGPMP.END_DT;
            WS_OUTPUT_DATA.WS_REMARK = TDRGPMP.REMARK;
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 444;
            B_MPAG();
            if (pgmRtn) return;
            T000_READ_NEXT();
            if (pgmRtn) return;
        }
        T000_ENDBR();
        if (pgmRtn) return;
    }
    public void B020_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGPMP);
        TDRGPMP.KEY.PROVD_NO = TDB6800_AWA_6800.PROVD_NO;
        TDRGPMP.KEY.PRD_NO = TDB6800_AWA_6800.PRD_NO;
        T000_READ_TDTGPMP();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_PROVD_NO = TDRGPMP.KEY.PROVD_NO;
        WS_OUTPUT_DATA.WS_PRD_NO = TDRGPMP.KEY.PRD_NO;
        WS_OUTPUT_DATA.WS_PROVD_NM = TDRGPMP.PROVD_NM;
        WS_OUTPUT_DATA.WS_PRD_NM = TDRGPMP.PRD_NM;
        WS_OUTPUT_DATA.WS_AMT = TDRGPMP.AMT;
        WS_OUTPUT_DATA.WS_PROD_CD = TDRGPMP.PROD_CD;
        WS_OUTPUT_DATA.WS_TERM = TDRGPMP.TERM;
        WS_OUTPUT_DATA.WS_PRICE = TDRGPMP.PRICE;
        WS_OUTPUT_DATA.WS_OLD_PRICE = TDRGPMP.OLD_PRICE;
        WS_OUTPUT_DATA.WS_INT_AMT = TDRGPMP.INT_AMT;
        WS_OUTPUT_DATA.WS_DIS_RT = TDRGPMP.DIS_RT;
        WS_OUTPUT_DATA.WS_DIS_INT = TDRGPMP.DIS_INT;
        WS_OUTPUT_DATA.WS_SALE_FEE = TDRGPMP.SALE_FEE;
        WS_OUTPUT_DATA.WS_INT_RATE = TDRGPMP.INT_RATE;
        WS_OUTPUT_DATA.WS_REV_AC = TDRGPMP.REV_AC;
        WS_OUTPUT_DATA.WS_VAL_FGL = TDRGPMP.VAL_FGL;
        WS_OUTPUT_DATA.WS_VAL_DT = TDRGPMP.VAL_DT;
        WS_OUTPUT_DATA.WS_END_DT = TDRGPMP.END_DT;
        WS_OUTPUT_DATA.WS_REMARK = TDRGPMP.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "TD680";
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 444;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGPMP);
        TDRGPMP.KEY.PROVD_NO = TDB6800_AWA_6800.PROVD_NO;
        TDRGPMP.KEY.PRD_NO = TDB6800_AWA_6800.PRD_NO;
        TDRGPMP.PROVD_NM = TDB6800_AWA_6800.PROVD_NM;
        TDRGPMP.PRD_NM = TDB6800_AWA_6800.PRD_NM;
        TDRGPMP.AMT = TDB6800_AWA_6800.SIN_BAL;
        TDRGPMP.PROD_CD = TDB6800_AWA_6800.PROD_CD;
        TDRGPMP.TERM = TDB6800_AWA_6800.TERM;
        TDRGPMP.PRICE = TDB6800_AWA_6800.SIN_PRIC;
        TDRGPMP.OLD_PRICE = TDB6800_AWA_6800.OLD_PRIC;
        TDRGPMP.INT_AMT = TDB6800_AWA_6800.INT_AMT;
        TDRGPMP.DIS_RT = TDB6800_AWA_6800.DIS_RT;
        TDRGPMP.DIS_INT = TDB6800_AWA_6800.DIS_INT;
        TDRGPMP.SALE_FEE = TDB6800_AWA_6800.SALE_FEE;
        TDRGPMP.INT_RATE = TDB6800_AWA_6800.INT_RATE;
        TDRGPMP.REV_AC = TDB6800_AWA_6800.REV_AC;
        TDRGPMP.VAL_FGL = 'Y';
        TDRGPMP.VAL_DT = TDB6800_AWA_6800.VAL_DT;
        TDRGPMP.END_DT = TDB6800_AWA_6800.END_DT;
        TDRGPMP.REMARK = TDB6800_AWA_6800.REMARK;
        TDRGPMP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRGPMP.CRT_DATE = SCCGWA.COMM_AREA.TR_DATE;
        TDRGPMP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRGPMP.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        T000_WRITE_TDTGPMP();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_PROVD_NO = TDRGPMP.KEY.PROVD_NO;
        WS_OUTPUT_DATA.WS_PRD_NO = TDRGPMP.KEY.PRD_NO;
        WS_OUTPUT_DATA.WS_PROVD_NM = TDRGPMP.PROVD_NM;
        WS_OUTPUT_DATA.WS_PRD_NM = TDRGPMP.PRD_NM;
        WS_OUTPUT_DATA.WS_AMT = TDRGPMP.AMT;
        WS_OUTPUT_DATA.WS_PROD_CD = TDRGPMP.PROD_CD;
        WS_OUTPUT_DATA.WS_TERM = TDRGPMP.TERM;
        WS_OUTPUT_DATA.WS_PRICE = TDRGPMP.PRICE;
        WS_OUTPUT_DATA.WS_OLD_PRICE = TDRGPMP.OLD_PRICE;
        WS_OUTPUT_DATA.WS_INT_AMT = TDRGPMP.INT_AMT;
        WS_OUTPUT_DATA.WS_DIS_RT = TDRGPMP.DIS_RT;
        WS_OUTPUT_DATA.WS_DIS_INT = TDRGPMP.DIS_INT;
        WS_OUTPUT_DATA.WS_SALE_FEE = TDRGPMP.SALE_FEE;
        WS_OUTPUT_DATA.WS_INT_RATE = TDRGPMP.INT_RATE;
        WS_OUTPUT_DATA.WS_REV_AC = TDRGPMP.REV_AC;
        WS_OUTPUT_DATA.WS_VAL_FGL = TDRGPMP.VAL_FGL;
        WS_OUTPUT_DATA.WS_VAL_DT = TDRGPMP.VAL_DT;
        WS_OUTPUT_DATA.WS_END_DT = TDRGPMP.END_DT;
        WS_OUTPUT_DATA.WS_REMARK = TDRGPMP.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "TD680";
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 444;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_CHANGE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGPMP);
        TDRGPMP.KEY.PROVD_NO = TDB6800_AWA_6800.PROVD_NO;
        TDRGPMP.KEY.PRD_NO = TDB6800_AWA_6800.PRD_NO;
        T000_REUPDATE_TDTGPMP();
        if (pgmRtn) return;
        TDRGPMP.PROVD_NM = TDB6800_AWA_6800.PROVD_NM;
        TDRGPMP.PRD_NM = TDB6800_AWA_6800.PRD_NM;
        TDRGPMP.AMT = TDB6800_AWA_6800.SIN_BAL;
        TDRGPMP.PROD_CD = TDB6800_AWA_6800.PROD_CD;
        TDRGPMP.TERM = TDB6800_AWA_6800.TERM;
        TDRGPMP.PRICE = TDB6800_AWA_6800.SIN_PRIC;
        TDRGPMP.OLD_PRICE = TDB6800_AWA_6800.OLD_PRIC;
        TDRGPMP.INT_AMT = TDB6800_AWA_6800.INT_AMT;
        TDRGPMP.DIS_RT = TDB6800_AWA_6800.DIS_RT;
        TDRGPMP.DIS_INT = TDB6800_AWA_6800.DIS_INT;
        TDRGPMP.SALE_FEE = TDB6800_AWA_6800.SALE_FEE;
        TDRGPMP.INT_RATE = TDB6800_AWA_6800.INT_RATE;
        TDRGPMP.REV_AC = TDB6800_AWA_6800.REV_AC;
        TDRGPMP.VAL_FGL = 'Y';
        TDRGPMP.VAL_DT = TDB6800_AWA_6800.VAL_DT;
        TDRGPMP.END_DT = TDB6800_AWA_6800.END_DT;
        TDRGPMP.REMARK = TDB6800_AWA_6800.REMARK;
        TDRGPMP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRGPMP.CRT_DATE = SCCGWA.COMM_AREA.TR_DATE;
        TDRGPMP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRGPMP.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        T000_REWRITE_TDTGPMP();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_PROVD_NO = TDRGPMP.KEY.PROVD_NO;
        WS_OUTPUT_DATA.WS_PRD_NO = TDRGPMP.KEY.PRD_NO;
        WS_OUTPUT_DATA.WS_PROVD_NM = TDRGPMP.PROVD_NM;
        WS_OUTPUT_DATA.WS_PRD_NM = TDRGPMP.PRD_NM;
        WS_OUTPUT_DATA.WS_AMT = TDRGPMP.AMT;
        WS_OUTPUT_DATA.WS_PROD_CD = TDRGPMP.PROD_CD;
        WS_OUTPUT_DATA.WS_TERM = TDRGPMP.TERM;
        WS_OUTPUT_DATA.WS_PRICE = TDRGPMP.PRICE;
        WS_OUTPUT_DATA.WS_OLD_PRICE = TDRGPMP.OLD_PRICE;
        WS_OUTPUT_DATA.WS_INT_AMT = TDRGPMP.INT_AMT;
        WS_OUTPUT_DATA.WS_DIS_RT = TDRGPMP.DIS_RT;
        WS_OUTPUT_DATA.WS_DIS_INT = TDRGPMP.DIS_INT;
        WS_OUTPUT_DATA.WS_SALE_FEE = TDRGPMP.SALE_FEE;
        WS_OUTPUT_DATA.WS_INT_RATE = TDRGPMP.INT_RATE;
        WS_OUTPUT_DATA.WS_REV_AC = TDRGPMP.REV_AC;
        WS_OUTPUT_DATA.WS_VAL_FGL = TDRGPMP.VAL_FGL;
        WS_OUTPUT_DATA.WS_VAL_DT = TDRGPMP.VAL_DT;
        WS_OUTPUT_DATA.WS_END_DT = TDRGPMP.END_DT;
        WS_OUTPUT_DATA.WS_REMARK = TDRGPMP.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "TD680";
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 444;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_DEL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGPMP);
        TDRGPMP.KEY.PROVD_NO = TDB6800_AWA_6800.PROVD_NO;
        TDRGPMP.KEY.PRD_NO = TDB6800_AWA_6800.PRD_NO;
        T000_REUPDATE_TDTGPMP();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.AC_DATE >= TDRGPMP.VAL_DT) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_RECORD_EFFTIVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_DEL_TDTGPMP();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_PROVD_NO = TDRGPMP.KEY.PROVD_NO;
        WS_OUTPUT_DATA.WS_PRD_NO = TDRGPMP.KEY.PRD_NO;
        WS_OUTPUT_DATA.WS_PROVD_NM = TDRGPMP.PROVD_NM;
        WS_OUTPUT_DATA.WS_PRD_NM = TDRGPMP.PRD_NM;
        WS_OUTPUT_DATA.WS_AMT = TDRGPMP.AMT;
        WS_OUTPUT_DATA.WS_PROD_CD = TDRGPMP.PROD_CD;
        WS_OUTPUT_DATA.WS_TERM = TDRGPMP.TERM;
        WS_OUTPUT_DATA.WS_PRICE = TDRGPMP.PRICE;
        WS_OUTPUT_DATA.WS_OLD_PRICE = TDRGPMP.OLD_PRICE;
        WS_OUTPUT_DATA.WS_INT_AMT = TDRGPMP.INT_AMT;
        WS_OUTPUT_DATA.WS_DIS_RT = TDRGPMP.DIS_RT;
        WS_OUTPUT_DATA.WS_DIS_INT = TDRGPMP.DIS_INT;
        WS_OUTPUT_DATA.WS_SALE_FEE = TDRGPMP.SALE_FEE;
        WS_OUTPUT_DATA.WS_INT_RATE = TDRGPMP.INT_RATE;
        WS_OUTPUT_DATA.WS_REV_AC = TDRGPMP.REV_AC;
        WS_OUTPUT_DATA.WS_VAL_FGL = TDRGPMP.VAL_FGL;
        WS_OUTPUT_DATA.WS_VAL_DT = TDRGPMP.VAL_DT;
        WS_OUTPUT_DATA.WS_END_DT = TDRGPMP.END_DT;
        WS_OUTPUT_DATA.WS_REMARK = TDRGPMP.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "TD680";
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 444;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B040_OUTPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void T000_WRITE_TDTGPMP() throws IOException,SQLException,Exception {
        TDTGPMP_RD = new DBParm();
        TDTGPMP_RD.TableName = "TDTGPMP";
        IBS.WRITE(SCCGWA, TDRGPMP, TDTGPMP_RD);
    }
    public void T000_REUPDATE_TDTGPMP() throws IOException,SQLException,Exception {
        TDTGPMP_RD = new DBParm();
        TDTGPMP_RD.TableName = "TDTGPMP";
        TDTGPMP_RD.upd = true;
        IBS.READ(SCCGWA, TDRGPMP, TDTGPMP_RD);
    }
    public void T000_REWRITE_TDTGPMP() throws IOException,SQLException,Exception {
        TDTGPMP_RD = new DBParm();
        TDTGPMP_RD.TableName = "TDTGPMP";
        IBS.REWRITE(SCCGWA, TDRGPMP, TDTGPMP_RD);
    }
    public void T000_STARTBR() throws IOException,SQLException,Exception {
        TDTGPMP_BR.rp = new DBParm();
        TDTGPMP_BR.rp.TableName = "TDTGPMP";
        IBS.STARTBR(SCCGWA, TDRGPMP, TDTGPMP_BR);
    }
    public void T000_STARTBR_ALL() throws IOException,SQLException,Exception {
        TDTGPMP_BR.rp = new DBParm();
        TDTGPMP_BR.rp.TableName = "TDTGPMP";
        TDTGPMP_BR.rp.where = "PROVD_NO = :TDRGPMP.KEY.PROVD_NO "
            + "AND PRD_NO = :TDRGPMP.KEY.PRD_NO";
        IBS.STARTBR(SCCGWA, TDRGPMP, this, TDTGPMP_BR);
    }
    public void T000_STARTBR_PRD() throws IOException,SQLException,Exception {
        TDTGPMP_BR.rp = new DBParm();
        TDTGPMP_BR.rp.TableName = "TDTGPMP";
        TDTGPMP_BR.rp.where = "PRD_NO = :TDRGPMP.KEY.PRD_NO";
        IBS.STARTBR(SCCGWA, TDRGPMP, this, TDTGPMP_BR);
    }
    public void T000_STARTBR_PROVD() throws IOException,SQLException,Exception {
        TDTGPMP_BR.rp = new DBParm();
        TDTGPMP_BR.rp.TableName = "TDTGPMP";
        TDTGPMP_BR.rp.where = "PROVD_NO = :TDRGPMP.KEY.PROVD_NO";
        IBS.STARTBR(SCCGWA, TDRGPMP, this, TDTGPMP_BR);
    }
    public void T000_READ_NEXT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRGPMP, this, TDTGPMP_BR);
    }
    public void T000_ENDBR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTGPMP_BR);
    }
    public void T000_DEL_TDTGPMP() throws IOException,SQLException,Exception {
        TDTGPMP_RD = new DBParm();
        TDTGPMP_RD.TableName = "TDTGPMP";
        IBS.DELETE(SCCGWA, TDRGPMP, TDTGPMP_RD);
    }
    public void T000_READ_TDTGPMP() throws IOException,SQLException,Exception {
        TDTGPMP_RD = new DBParm();
        TDTGPMP_RD.TableName = "TDTGPMP";
        IBS.READ(SCCGWA, TDRGPMP, TDTGPMP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
