package com.hisun.LN;

import com.hisun.IB.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSLOOA {
    LNZSLOOA_WS_DATA_OUTPUT WS_DATA_OUTPUT;
    int JIBS_tmp_int;
    brParm LNTCLNO_BR = new brParm();
    DBParm LNTCLNO_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    LNZSLOOA_WS_VARIABLES WS_VARIABLES = new LNZSLOOA_WS_VARIABLES();
    LNZSLOOA_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new LNZSLOOA_WS_BROWSE_OUTPUT();
    IBCQINF IBCQINF = new IBCQINF();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGBPA_BP_AREA BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNRCLNO LNRCLNO = new LNRCLNO();
    LNCRCLNO LNCRCLNO = new LNCRCLNO();
    LNRRELA LNRRELA = new LNRRELA();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRICTL LNRICTL = new LNRICTL();
    LNRICTL_WS_DB_VARS WS_DB_VARS = new LNRICTL_WS_DB_VARS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGAPV SCCGAPV;
    LNCSLOOA LNCSLOOA;
    public void MP(SCCGWA SCCGWA, LNCSLOOA LNCSLOOA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSLOOA = LNCSLOOA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSLOOA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) SC_AREA.APVL_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, LNRLOAN);
        LNCRCLNO.RC.RC_MMO = "LN";
        LNCRCLNO.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        if (LNCSLOOA.FUNC == 'C') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSLOOA.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSLOOA.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSLOOA.FUNC == 'I') {
            B050_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCSLOOA.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        if (LNCSLOOA.FUNC == 'C' 
            || LNCSLOOA.FUNC == 'U') {
            if (!LNCSLOOA.CCY.equalsIgnoreCase("344")) {
                CEP.ERR(SCCGWA, ERROR_MSG.LN_NOT_ALLOW_CCY);
            }
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.AC_NO = LNCSLOOA.I_BK_NO;
            S000_CALL_IBZQINF();
            if (pgmRtn) return;
            if (!IBCQINF.OUTPUT_DATA.CORRAC_NO.equalsIgnoreCase(LNRCLNO.KEY.CL_O_BK_NO)) {
                CEP.ERR(SCCGWA, ERROR_MSG.LN_AC_INPUT_ERROR);
            }
        }
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        B020_CHECK_ADD_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRCLNO);
        IBS.init(SCCGWA, LNCRCLNO);
        LNCRCLNO.FUNC = 'A';
        LNRCLNO.KEY.CL_CCY = LNCSLOOA.CCY;
        LNRCLNO.KEY.CL_I_BK_NO = LNCSLOOA.I_BK_NO;
        LNRCLNO.KEY.CL_O_BK_NO = LNCSLOOA.O_BK_NO;
        LNRCLNO.CL_O_BK_NM = LNCSLOOA.O_BK_NM;
        LNRCLNO.CL_SWT_CD = LNCSLOOA.SWT_CD;
        LNRCLNO.CL_METHOD = LNCSLOOA.METHOD;
        LNRCLNO.KEY.CL_BUSI_KND = LNCSLOOA.BUSI_KND;
        LNRCLNO.CL_REMARK = LNCSLOOA.REMARK;
        LNRCLNO.CL_STS = '0';
        S000_CALL_LNZRCLNO();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        B030_CHECK_UPDATE_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRCLNO);
        IBS.init(SCCGWA, LNCRCLNO);
        LNCRCLNO.FUNC = 'R';
        LNRCLNO.KEY.CL_BUSI_KND = LNCSLOOA.BUSI_KND;
        LNRCLNO.KEY.CL_CCY = LNCSLOOA.CCY;
        LNRCLNO.KEY.CL_I_BK_NO = LNCSLOOA.I_BK_NO;
        LNRCLNO.KEY.CL_O_BK_NO = LNCSLOOA.O_BK_NO;
        S000_CALL_LNZRCLNO();
        if (pgmRtn) return;
        WS_VARIABLES.CLNO_CRT_DATE = LNRCLNO.CRT_DATE;
        WS_VARIABLES.CLNO_CRT_TLR = LNRCLNO.CRT_TLR;
        IBS.init(SCCGWA, LNRCLNO);
        IBS.init(SCCGWA, LNCRCLNO);
        LNCRCLNO.FUNC = 'U';
        LNRCLNO.KEY.CL_I_BK_NO = LNCSLOOA.I_BK_NO;
        LNRCLNO.KEY.CL_O_BK_NO = LNCSLOOA.O_BK_NO;
        LNRCLNO.CL_O_BK_NM = LNCSLOOA.O_BK_NM;
        LNRCLNO.CL_SWT_CD = LNCSLOOA.SWT_CD;
        LNRCLNO.CL_METHOD = LNCSLOOA.METHOD;
        LNRCLNO.CL_REMARK = LNCSLOOA.REMARK;
        LNRCLNO.CRT_DATE = WS_VARIABLES.CLNO_CRT_DATE;
        LNRCLNO.CRT_TLR = WS_VARIABLES.CLNO_CRT_TLR;
        S000_CALL_LNZRCLNO();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        B040_CHECK_DELETE_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRCLNO);
        IBS.init(SCCGWA, LNCRCLNO);
        LNCRCLNO.FUNC = 'R';
        LNRCLNO.KEY.CL_BUSI_KND = LNCSLOOA.BUSI_KND;
        LNRCLNO.KEY.CL_CCY = LNCSLOOA.CCY;
        LNRCLNO.KEY.CL_I_BK_NO = LNCSLOOA.I_BK_NO;
        LNRCLNO.KEY.CL_O_BK_NO = LNCSLOOA.O_BK_NO;
        S000_CALL_LNZRCLNO();
        if (pgmRtn) return;
        LNCRCLNO.FUNC = 'D';
        S000_CALL_LNZRCLNO();
        if (pgmRtn) return;
    }
    public void B050_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCLNO);
        IBS.init(SCCGWA, LNRCLNO);
        LNCRCLNO.FUNC = 'I';
        LNRCLNO.KEY.CL_BUSI_KND = LNCSLOOA.BUSI_KND;
        LNRCLNO.KEY.CL_CCY = LNCSLOOA.CCY;
        LNRCLNO.KEY.CL_I_BK_NO = LNCSLOOA.I_BK_NO;
        LNRCLNO.KEY.CL_O_BK_NO = LNCSLOOA.O_BK_NO;
        S000_CALL_LNZRCLNO();
        if (pgmRtn) return;
        if (LNCRCLNO.RETURN_INFO == 'F') {
            B300_OUTPUT_PROCESS();
            if (pgmRtn) return;
        }
        if (LNCRCLNO.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_CLNO_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_ADD_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCLNO);
        IBS.init(SCCGWA, LNCRCLNO);
        LNCRCLNO.FUNC = 'I';
        LNRCLNO.KEY.CL_BUSI_KND = LNCSLOOA.BUSI_KND;
        LNRCLNO.KEY.CL_CCY = LNCSLOOA.CCY;
        LNRCLNO.KEY.CL_I_BK_NO = LNCSLOOA.I_BK_NO;
        LNRCLNO.KEY.CL_O_BK_NO = LNCSLOOA.O_BK_NO;
        S000_CALL_LNZRCLNO();
        if (pgmRtn) return;
        if (LNCRCLNO.RETURN_INFO == 'F') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_CLNO_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_CHECK_UPDATE_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCLNO);
        IBS.init(SCCGWA, LNCRCLNO);
        LNCRCLNO.FUNC = 'I';
        LNRCLNO.KEY.CL_BUSI_KND = LNCSLOOA.BUSI_KND;
        LNRCLNO.KEY.CL_CCY = LNCSLOOA.CCY;
        LNRCLNO.KEY.CL_I_BK_NO = LNCSLOOA.I_BK_NO;
        LNRCLNO.KEY.CL_O_BK_NO = LNCSLOOA.O_BK_NO;
        S000_CALL_LNZRCLNO();
        if (pgmRtn) return;
        if (LNCRCLNO.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_CLNO_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_CHECK_DELETE_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCLNO);
        IBS.init(SCCGWA, LNRCLNO);
        LNCRCLNO.FUNC = 'I';
        LNRCLNO.KEY.CL_BUSI_KND = LNCSLOOA.BUSI_KND;
        LNRCLNO.KEY.CL_CCY = LNCSLOOA.CCY;
        LNRCLNO.KEY.CL_I_BK_NO = LNCSLOOA.I_BK_NO;
        LNRCLNO.KEY.CL_O_BK_NO = LNCSLOOA.O_BK_NO;
        S000_CALL_LNZRCLNO();
        if (pgmRtn) return;
        if (LNCRCLNO.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_CLNO_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_BROWSE_RECORD_BY_ALL() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC_BY_ALL();
        if (pgmRtn) return;
        T000_READNEXT_PROC();
        if (pgmRtn) return;
        if (WS_VARIABLES.REC_CLNO_FLG != 'N') {
            WS_VARIABLES.WS_DATE.IDX = 0;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            for (WS_VARIABLES.WS_DATE.IDX = 1; WS_VARIABLES.REC_CLNO_FLG != 'N' 
                && (WS_VARIABLES.WS_DATE.IDX <= WS_VARIABLES.WS_DATE.PAGE_ROW); WS_VARIABLES.WS_DATE.IDX += 1) {
                B300_OUTPUT_MPAG_PROCESS();
                if (pgmRtn) return;
                WS_DB_VARS.START_NUM += 1;
                T000_READNEXT_PROC();
                if (pgmRtn) return;
            }
            if (WS_VARIABLES.REC_CLNO_FLG == 'N') {
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.IDX - 1;
                WS_VARIABLES.WS_DATE.TOTAL_NUM = ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW + WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATE.PAGE_ROW = (short) WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_DATA_OUTPUT = new LNZSLOOA_WS_DATA_OUTPUT();
                WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
            } else {
                R000_GROUP_ALL();
                if (pgmRtn) return;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.TOTAL_NUM % WS_VARIABLES.WS_DATE.PAGE_ROW;
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = (short) ((WS_VARIABLES.WS_DATE.TOTAL_NUM - WS_VARIABLES.WS_DATE.BAL_CNT) / WS_VARIABLES.WS_DATE.PAGE_ROW);
                if (WS_VARIABLES.WS_DATE.BAL_CNT != 0) {
                    WS_VARIABLES.WS_DATE.TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_VARIABLES.WS_DATE.TOTAL_PAGE = 1;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_DATE.PAGE_ROW = 0;
            WS_DATA_OUTPUT = new LNZSLOOA_WS_DATA_OUTPUT();
            WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
        }
        T000_ENDBR_PROC();
        if (pgmRtn) return;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATE.TOTAL_PAGE;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_DATE.TOTAL_NUM;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATE.LAST_PAGE;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_PAGE_ROW = WS_VARIABLES.WS_DATE.PAGE_ROW;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LN540";
        SCCFMT.DATA_PTR = WS_BROWSE_OUTPUT;
        SCCFMT.DATA_LEN = 7354;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_VARIABLES.WS_OUTPUT.O_BK_NM = LNRCLNO.CL_O_BK_NM;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.O_BK_NM);
        WS_VARIABLES.WS_OUTPUT.SWT_CD = LNRCLNO.CL_SWT_CD;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.SWT_CD);
        WS_VARIABLES.WS_OUTPUT.METHOD = LNRCLNO.CL_METHOD;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.METHOD);
        WS_VARIABLES.WS_OUTPUT.BUSI_KND = LNRCLNO.KEY.CL_BUSI_KND;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.BUSI_KND);
        WS_VARIABLES.WS_OUTPUT.REMARK = LNRCLNO.CL_REMARK;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.REMARK);
        SCCFMT.FMTID = "LN540";
        SCCFMT.DATA_PTR = WS_VARIABLES.WS_OUTPUT;
        SCCFMT.DATA_LEN = 258;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF, true);
        CEP.TRC(SCCGWA, IBCQINF.RC.RC_CODE);
        if (IBCQINF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, IBCQINF.RC);
        }
    }
    public void B300_OUTPUT_MPAG_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_DATA_OUTPUT);
        WS_DATA_OUTPUT.B_CCY = LNRCLNO.KEY.CL_CCY;
        WS_DATA_OUTPUT.B_I_BK_NO = LNRCLNO.KEY.CL_I_BK_NO;
        if (LNRCLNO.KEY.CL_O_BK_NO.trim().length() == 0) WS_DATA_OUTPUT.B_O_BK_NO = 0;
        else WS_DATA_OUTPUT.B_O_BK_NO = Integer.parseInt(LNRCLNO.KEY.CL_O_BK_NO);
        WS_DATA_OUTPUT.B_O_BK_NM = LNRCLNO.CL_O_BK_NM;
        WS_DATA_OUTPUT.B_SWT_CD = LNRCLNO.CL_SWT_CD;
        WS_DATA_OUTPUT.B_METHOD = "" + LNRCLNO.CL_METHOD;
        JIBS_tmp_int = WS_DATA_OUTPUT.B_METHOD.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) WS_DATA_OUTPUT.B_METHOD = "0" + WS_DATA_OUTPUT.B_METHOD;
        WS_DATA_OUTPUT.B_BUSI_KND = LNRCLNO.KEY.CL_BUSI_KND.charAt(0);
        WS_DATA_OUTPUT.B_STS = LNRCLNO.CL_STS;
        WS_DATA_OUTPUT.B_REMARK = LNRCLNO.CL_REMARK;
    }
    public void R000_GROUP_ALL() throws IOException,SQLException,Exception {
        T000_02_GROUP_REC_PROC();
        if (pgmRtn) return;
        WS_VARIABLES.WS_DATE.TOTAL_NUM = WS_DB_VARS.CNT;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.TOTAL_NUM);
    }
    public void T000_STARTBR_PROC_BY_ALL() throws IOException,SQLException,Exception {
        LNTCLNO_BR.rp = new DBParm();
        LNTCLNO_BR.rp.TableName = "LNTCLNO";
        LNTCLNO_BR.rp.order = "CL_CCY , CL_BUSI_KND";
        IBS.STARTBR(SCCGWA, LNRCLNO, LNTCLNO_BR);
    }
    public void T000_02_GROUP_REC_PROC() throws IOException,SQLException,Exception {
        LNTCLNO_RD = new DBParm();
        LNTCLNO_RD.TableName = "LNTCLNO";
        LNTCLNO_RD.set = "WS-CNT=COUNT(*)";
        IBS.GROUP(SCCGWA, LNRCLNO, this, LNTCLNO_RD);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRCLNO, this, LNTCLNO_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.REC_CLNO_FLG = 'N';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTCLNO_BR);
    }
    public void S000_CALL_LNZRCLNO() throws IOException,SQLException,Exception {
        LNCRCLNO.REC_PTR = LNRCLNO;
        LNCRCLNO.REC_LEN = 1072;
        CEP.TRC(SCCGWA, LNRCLNO);
        CEP.TRC(SCCGWA, LNCRCLNO);
        IBS.CALLCPN(SCCGWA, "LN-R-CLNO-MAIN", LNCRCLNO);
        if (LNCRCLNO.RETURN_INFO != 'F' 
            && LNCRCLNO.RETURN_INFO != 'E' 
            && LNCRCLNO.RETURN_INFO != 'N') {
            WS_VARIABLES.ERR_MSG = "" + LNCRCLNO.RC.RC_CODE;
            JIBS_tmp_int = WS_VARIABLES.ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_VARIABLES.ERR_MSG = "0" + WS_VARIABLES.ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
