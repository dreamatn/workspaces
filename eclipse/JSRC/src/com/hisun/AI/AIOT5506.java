package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5506 {
    brParm BPTVCHT_BR = new brParm();
    brParm BPTVCHH_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_VCHT_FLG = ' ';
    char WS_VCHH_FLG = ' ';
    AIOT5506_WS_OUTPUT_DATA WS_OUTPUT_DATA = new AIOT5506_WS_OUTPUT_DATA();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRVCHT BPRVCHT = new BPRVCHT();
    BPRVCHH BPRVCHH = new BPRVCHH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5506_AWA_5506 AIB5506_AWA_5506;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT5506 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5506_AWA_5506>");
        AIB5506_AWA_5506 = (AIB5506_AWA_5506) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, AIB5506_AWA_5506.TR_DT);
        CEP.TRC(SCCGWA, AIB5506_AWA_5506.VCH_NO);
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
        if (AIB5506_AWA_5506.TR_DT == SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, BPRVCHT);
            WS_VCHT_FLG = ' ';
            BPRVCHT.KEY.AC_DATE = AIB5506_AWA_5506.TR_DT;
            BPRVCHT.KEY.SET_NO = AIB5506_AWA_5506.VCH_NO;
            T000_STARTBR_BPTVCHT();
            if (pgmRtn) return;
            T000_READNEXT_BPTVCHT();
            if (pgmRtn) return;
            if (WS_VCHT_FLG == 'Y') {
                B020_01_OUT_TITLE();
                if (pgmRtn) return;
            }
            while (WS_VCHT_FLG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                B020_02_OUT_BRW_DATA();
                if (pgmRtn) return;
                T000_READNEXT_BPTVCHT();
                if (pgmRtn) return;
            }
            T000_ENDBR_BPTVCHT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPRVCHH);
            WS_VCHH_FLG = ' ';
            BPRVCHH.KEY.AC_DATE = AIB5506_AWA_5506.TR_DT;
            BPRVCHH.KEY.SET_NO = AIB5506_AWA_5506.VCH_NO;
            T000_STARTBR_BPTVCHH();
            if (pgmRtn) return;
            T000_READNEXT_BPTVCHH();
            if (pgmRtn) return;
            if (WS_VCHH_FLG == 'Y') {
                B020_01_OUT_TITLE();
                if (pgmRtn) return;
            }
            while (WS_VCHH_FLG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                R000_TRANS_DATA_VCHH_VCHT();
                if (pgmRtn) return;
                B020_02_OUT_BRW_DATA();
                if (pgmRtn) return;
                T000_READNEXT_BPTVCHH();
                if (pgmRtn) return;
            }
            T000_ENDBR_BPTVCHH();
            if (pgmRtn) return;
        }
    }
    public void B020_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_OUT_TR_DT = BPRVCHT.TR_DATE;
        WS_OUTPUT_DATA.WS_OUT_TR_TM = BPRVCHT.TR_TIME;
        WS_OUTPUT_DATA.WS_OUT_AC_DT = BPRVCHT.KEY.AC_DATE;
        WS_OUTPUT_DATA.WS_OUT_VAL_DT = BPRVCHT.VAL_DATE;
        WS_OUTPUT_DATA.WS_OUT_VCHNO = BPRVCHT.KEY.SET_NO;
        WS_OUTPUT_DATA.WS_OUT_SEQ = BPRVCHT.KEY.SET_SEQ;
        WS_OUTPUT_DATA.WS_OUT_JRNNO = BPRVCHT.JRN_NO;
        WS_OUTPUT_DATA.WS_OUT_TX_CD = BPRVCHT.TR_CODE;
        WS_OUTPUT_DATA.WS_OUT_TYP = BPRVCHT.GEN_TYPE;
        WS_OUTPUT_DATA.WS_OUT_TR_BR = BPRVCHT.TR_BR;
        WS_OUTPUT_DATA.WS_OUT_BR = BPRVCHT.BR;
        WS_OUTPUT_DATA.WS_OUT_ITM = BPRVCHT.ITM;
        WS_OUTPUT_DATA.WS_OUT_CCY = BPRVCHT.CCY;
        WS_OUTPUT_DATA.WS_OUT_DRCRFLG = BPRVCHT.SIGN;
        WS_OUTPUT_DATA.WS_OUT_AMT = BPRVCHT.AMT;
        WS_OUTPUT_DATA.WS_OUT_EC_IND = BPRVCHT.EC_IND;
        WS_OUTPUT_DATA.WS_OUT_VCHRF_NO = BPRVCHT.AC_NO;
        WS_OUTPUT_DATA.WS_OUT_BR_AC = BPRVCHT.MIB_AC;
        WS_OUTPUT_DATA.WS_OUT_CI_NO = BPRVCHT.CI_NO;
        WS_OUTPUT_DATA.WS_OUT_OPPO_NO = BPRVCHT.THEIR_AC;
        WS_OUTPUT_DATA.WS_OUT_BSREF_NO = BPRVCHT.OTHSYS_KEY;
        WS_OUTPUT_DATA.WS_OUT_PRDMO_CD = BPRVCHT.CNTR_TYPE;
        WS_OUTPUT_DATA.WS_OUT_PROD_CD = BPRVCHT.PROD_CODE;
        WS_OUTPUT_DATA.WS_OUT_EVEN_CD = BPRVCHT.EVENT_CODE;
        WS_OUTPUT_DATA.WS_OUT_GL_MSTNO = BPRVCHT.GLMST;
        WS_OUTPUT_DATA.WS_OUT_APP = BPRVCHT.AP_MMO;
        WS_OUTPUT_DATA.WS_OUT_CHNL_NO = BPRVCHT.CHNL_NO;
        WS_OUTPUT_DATA.WS_OUT_TLR_NO = BPRVCHT.TR_TELLER;
        WS_OUTPUT_DATA.WS_OUT_ODE_FLG = BPRVCHT.ODE_FLG;
        WS_OUTPUT_DATA.WS_OUT_REF_NO = BPRVCHT.REF_NO;
        WS_OUTPUT_DATA.WS_OUT_RED_FLG = BPRVCHT.RED_FLG;
        WS_OUTPUT_DATA.WS_OUT_CREV_NO = BPRVCHT.CRVS_NO;
        WS_OUTPUT_DATA.WS_OUT_NARR_CD = BPRVCHT.NARR_CD;
        if (BPRVCHT.ODE_GRP_NO == 0) {
            WS_OUTPUT_DATA.WS_OUT_TXN_NARR = BPRVCHT.POST_NARR;
        } else {
            WS_OUTPUT_DATA.WS_OUT_TXN_NARR = " ";
        }
        WS_OUTPUT_DATA.WS_OUT_DESC = BPRVCHT.DESC;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 524;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_VCHH_VCHT() throws IOException,SQLException,Exception {
        BPRVCHT.KEY.PART_NO = (short) BPRVCHH.KEY.PART_NO;
        BPRVCHT.KEY.AC_DATE = BPRVCHH.KEY.AC_DATE;
        BPRVCHT.KEY.SET_NO = BPRVCHH.KEY.SET_NO;
        BPRVCHT.KEY.SET_SEQ = BPRVCHH.KEY.SET_SEQ;
        BPRVCHT.AI_SEQ = BPRVCHH.AI_SEQ;
        BPRVCHT.JRN_NO = BPRVCHH.JRN_NO;
        BPRVCHT.AP_MMO = BPRVCHH.AP_MMO;
        BPRVCHT.TR_CODE = BPRVCHH.TR_CODE;
        BPRVCHT.TR_MMO = BPRVCHH.TR_MMO;
        BPRVCHT.TR_DATE = BPRVCHH.TR_DATE;
        BPRVCHT.TR_TIME = BPRVCHH.TR_TIME;
        BPRVCHT.TR_BK = BPRVCHH.TR_BK;
        BPRVCHT.TR_BR = BPRVCHH.TR_BR;
        BPRVCHT.TR_TELLER = BPRVCHH.TR_TELLER;
        BPRVCHT.TM_NO = BPRVCHH.TM_NO;
        BPRVCHT.CHNL_NO = BPRVCHH.CHNL_NO;
        BPRVCHT.EC_IND = BPRVCHH.EC_IND;
        BPRVCHT.ORG_DATE = BPRVCHH.ORG_DATE;
        BPRVCHT.ORG_VCHNO = BPRVCHH.ORG_VCHNO;
        BPRVCHT.GEN_TYPE = BPRVCHH.GEN_TYPE;
        BPRVCHT.TR_TYPE = BPRVCHH.TR_TYPE;
        BPRVCHT.ODE_FLG = BPRVCHH.ODE_FLG;
        BPRVCHT.ODE_GRP_NO = BPRVCHH.ODE_GRP_NO;
        BPRVCHT.OTH_TR_DATE = BPRVCHH.OTH_TR_DATE;
        BPRVCHT.OTHSYS_ID = BPRVCHH.OTHSYS_ID;
        BPRVCHT.OTHSYS_KEY = BPRVCHH.OTHSYS_KEY;
        BPRVCHT.ANS_CD1 = BPRVCHH.ANS_CD1;
        BPRVCHT.ANS_CD2 = BPRVCHH.ANS_CD2;
        BPRVCHT.ANS_CD3 = BPRVCHH.ANS_CD3;
        BPRVCHT.ANS_CD4 = BPRVCHH.ANS_CD4;
        BPRVCHT.ANS_CD5 = BPRVCHH.ANS_CD5;
        BPRVCHT.ANS_CD6 = BPRVCHH.ANS_CD6;
        BPRVCHT.ANS_CD7 = BPRVCHH.ANS_CD7;
        BPRVCHT.ANS_CD8 = BPRVCHH.ANS_CD8;
        BPRVCHT.ANS_CD9 = BPRVCHH.ANS_CD9;
        BPRVCHT.ANS_CD10 = BPRVCHH.ANS_CD10;
        BPRVCHT.BOOK_FLG = BPRVCHH.BOOK_FLG;
        BPRVCHT.BR = BPRVCHH.BR;
        BPRVCHT.ITM = BPRVCHH.ITM;
        BPRVCHT.CCY = BPRVCHH.CCY;
        BPRVCHT.SIGN = BPRVCHH.SIGN;
        BPRVCHT.AMT = BPRVCHH.AMT;
        BPRVCHT.VAL_DATE = BPRVCHH.VAL_DATE;
        BPRVCHT.CNTR_TYPE = BPRVCHH.CNTR_TYPE;
        BPRVCHT.PROD_CODE = BPRVCHH.PROD_CODE;
        BPRVCHT.AC_NO = BPRVCHH.AC_NO;
        BPRVCHT.MIB_AC = BPRVCHH.MIB_NO;
        BPRVCHT.EVENT_CODE = BPRVCHH.EVENT_CODE;
        BPRVCHT.GLMST = BPRVCHH.GLMST;
        BPRVCHT.CNTR_TYPE_REL = BPRVCHH.CNTR_TYPE_REL;
        BPRVCHT.PROD_CODE_REL = BPRVCHH.PROD_CODE_REL;
        BPRVCHT.AC_NO_REL = BPRVCHH.AC_NO_REL;
        BPRVCHT.EVENT_CODE_REL = BPRVCHH.EVENT_CODE_REL;
        BPRVCHT.GLMST_REL = BPRVCHH.GLMST_REL;
        BPRVCHT.CI_NO = BPRVCHH.CI_NO;
        BPRVCHT.REF_NO = BPRVCHH.REF_NO;
        BPRVCHT.RED_FLG = BPRVCHH.RED_FLG;
        BPRVCHT.CRVS_NO = BPRVCHH.CRVS_NO;
        BPRVCHT.CRVS_SEQ = BPRVCHH.CRVS_SEQ;
        BPRVCHT.ONL_GMIB_FLG = BPRVCHH.ONL_GMIB_FLG;
        BPRVCHT.ONL_GVCH_FLG = BPRVCHH.ONL_GVCH_FLG;
        BPRVCHT.PORTFO_CD = BPRVCHH.PORTFO_CD;
        BPRVCHT.TR_GUP = BPRVCHH.TR_GUP;
        BPRVCHT.CHQ_NO = BPRVCHH.CHQ_NO;
        BPRVCHT.POST_DATE = BPRVCHH.POST_DATE;
        BPRVCHT.POST_NARR = BPRVCHH.POST_NARR;
        BPRVCHT.NARR_CD = BPRVCHH.NARR_CD;
        BPRVCHT.DESC = BPRVCHH.DESC;
        BPRVCHT.EFF_DAYS = BPRVCHH.EFF_DAYS;
        BPRVCHT.PRINT_FLG = BPRVCHH.PRINT_FLG;
        BPRVCHT.SUSPENSE_FLG = BPRVCHH.SUSPENSE_FLG;
        BPRVCHT.SUSPENSE_RSN = BPRVCHH.SUSPENSE_RSN;
        BPRVCHT.PAY_MAN = BPRVCHH.PAY_MAN;
        BPRVCHT.PAY_BR = BPRVCHH.PAY_BR;
        BPRVCHT.THEIR_AC = BPRVCHH.THEIR_AC;
        BPRVCHT.THEIR_CCY = BPRVCHH.THEIR_CCY;
        BPRVCHT.THEIR_AMT = BPRVCHH.THEIR_AMT;
        BPRVCHT.THEIR_RAT = BPRVCHH.THEIR_RAT;
        BPRVCHT.SETTLE_DT = BPRVCHH.SETTLE_DT;
        BPRVCHT.TLR_ID = BPRVCHH.TLR_ID;
        BPRVCHT.TLR_BR = BPRVCHH.TLR_BR;
        BPRVCHT.TLR_BR = BPRVCHH.TLR_BR;
        BPRVCHT.OTH_MAKER = BPRVCHH.OTH_MAKER;
        BPRVCHT.OTH_CHECKER = BPRVCHH.OTH_CHECKER;
        BPRVCHT.FLR = BPRVCHH.FLR;
        BPRVCHT.RES_CENT = BPRVCHH.RES_CENT;
        BPRVCHT.LINE = BPRVCHH.LINE;
        BPRVCHT.INTER_CLEAR = BPRVCHH.INTER_CLEAR;
        BPRVCHT.RESERVE = BPRVCHH.RESERVE;
        BPRVCHT.REQSYS_ID = BPRVCHH.REQSYS_ID;
        BPRVCHT.REQSYS_DT = BPRVCHH.REQSYS_DT;
        BPRVCHT.REQSYS_KEY = BPRVCHH.REQSYS_KEY;
        BPRVCHT.UPDTBL_DATE = BPRVCHH.UPDTBL_DATE;
        BPRVCHT.TS = BPRVCHH.TS;
    }
    public void T000_STARTBR_BPTVCHT() throws IOException,SQLException,Exception {
        BPTVCHT_BR.rp = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_BR.rp.TableName = "BPTVCHT1";
        else BPTVCHT_BR.rp.TableName = "BPTVCHT2";
        BPTVCHT_BR.rp.where = "AC_DATE = :BPRVCHT.KEY.AC_DATE "
            + "AND SET_NO = :BPRVCHT.KEY.SET_NO";
        BPTVCHT_BR.rp.order = "AC_DATE,SET_NO,SET_SEQ";
        IBS.STARTBR(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
    }
    public void T000_READNEXT_BPTVCHT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VCHT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VCHT_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTVCHT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTVCHT_BR);
    }
    public void T000_STARTBR_BPTVCHH() throws IOException,SQLException,Exception {
        BPTVCHH_BR.rp = new DBParm();
        BPTVCHH_BR.rp.TableName = "BPTVCHH";
        BPTVCHH_BR.rp.where = "AC_DATE = :BPRVCHH.KEY.AC_DATE "
            + "AND SET_NO = :BPRVCHH.KEY.SET_NO";
        BPTVCHH_BR.rp.order = "AC_DATE,SET_NO,SET_SEQ";
        IBS.STARTBR(SCCGWA, BPRVCHH, this, BPTVCHH_BR);
    }
    public void T000_READNEXT_BPTVCHH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRVCHH, this, BPTVCHH_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VCHH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VCHH_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTVCHH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTVCHH_BR);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
