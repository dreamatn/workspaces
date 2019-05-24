package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT7993 {
    DBParm BPTTLT_RD;
    brParm BPTTLT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_OUTPUT_FMT = "BP832";
    int WS_TOT_CNT = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    char WS_FRZ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRTLT BPRTLT = new BPRTLT();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPCO7933 BPCO7933 = new BPCO7933();
    SCCGWA SCCGWA;
    BPB7993_AWA_7993 BPB7993_AWA_7993;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT7993 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB7993_AWA_7993>");
        BPB7993_AWA_7993 = (BPB7993_AWA_7993) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB7993_AWA_7993.TLR_BR);
        CEP.TRC(SCCGWA, BPB7993_AWA_7993.STR_POS);
        if (BPB7993_AWA_7993.TLR_BR == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        B040_TLT_INFO_BRO();
        if (pgmRtn) return;
        B060_02_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B060_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCO7933);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        BPCO7933.TOTAL_NUM = WS_TOT_CNT;
        if (WS_FRZ_FLG == 'N') {
            BPCO7933.END_FLG = 'Y';
        }
        SCCFMT.DATA_PTR = BPCO7933;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        BPCO7933.TIMES[WS_I-1].TLR = BPRTLT.KEY.TLR;
        BPCO7933.TIMES[WS_I-1].TLR_BR = BPRTLT.TLR_BR;
        BPCO7933.TIMES[WS_I-1].EFF_DATE = BPRTLT.EFF_DT;
        BPCO7933.TIMES[WS_I-1].EXP_DATE = BPRTLT.EXP_DT;
        BPCO7933.TIMES[WS_I-1].TLR_CN_NM = BPRTLT.TLR_CN_NM;
        BPCO7933.TIMES[WS_I-1].TLR_TYP = BPRTLT.TLR_TYP;
        BPCO7933.TIMES[WS_I-1].TLR_LVL = BPRTLT.TLR_LVL;
        BPCO7933.TIMES[WS_I-1].TELE = BPRTLT.TELE;
        BPCO7933.TIMES[WS_I-1].PST_ADDRESS = BPRTLT.PST_ADDRESS;
        BPCO7933.TIMES[WS_I-1].TLR_STSW = BPRTLT.TLR_STSW;
        BPCO7933.TIMES[WS_I-1].TLR_STS = BPRTLT.TLR_STS;
        BPCO7933.TIMES[WS_I-1].SIGN_STS = BPRTLT.SIGN_STS;
        BPCO7933.TIMES[WS_I-1].SIGN_DT = BPRTLT.SIGN_DT;
        BPCO7933.TIMES[WS_I-1].SIGN_TIMES = BPRTLT.SIGN_TIMES;
        BPCO7933.TIMES[WS_I-1].SIGN_TRM = BPRTLT.SIGN_TRM;
        BPCO7933.TIMES[WS_I-1].LAST_UPD_DT = BPRTLT.UPD_DT;
        BPCO7933.TIMES[WS_I-1].LAST_UPD_TLR = BPRTLT.UPD_TLR;
        BPCO7933.TIMES[WS_I-1].LAST_JRN = BPRTLT.LAST_JRN;
        BPCO7933.TIMES[WS_I-1].ACC_VCH_NO = BPRTLT.ACC_VCH_NO;
        BPCO7933.TIMES[WS_I-1].REG_TYP = BPRTLT.REG_TYP;
        BPCO7933.TIMES[WS_I-1].TLR_CRD_NO = BPRTLT.TLR_CRD_NO;
        BPCO7933.TOD_REC_NUM = WS_I;
        BPCO7933.END_POS = BPRTLT.KEY.TLR;
        BPCO7933.END_FLG = 'N';
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, BPCO7933.TOTAL_NUM);
        CEP.TRC(SCCGWA, BPCO7933.TOD_REC_NUM);
        CEP.TRC(SCCGWA, BPCO7933.END_POS);
        CEP.TRC(SCCGWA, BPCO7933.END_FLG);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].TLR);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].TLR_BR);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].EFF_DATE);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].EXP_DATE);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].TLR_TYP);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].TLR_LVL);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].TELE);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].PST_ADDRESS);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].TLR_STSW);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].TLR_STS);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].SIGN_STS);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].SIGN_DT);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].SIGN_TIMES);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].SIGN_TRM);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].LAST_UPD_DT);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].LAST_UPD_TLR);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].LAST_JRN);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].ACC_VCH_NO);
        CEP.TRC(SCCGWA, BPCO7933.TIMES[WS_I-1].TLR_CRD_NO);
    }
    public void B040_TLT_INFO_BRO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCO7933);
        if (BPB7993_AWA_7993.STR_POS.trim().length() == 0) {
            T000_SUMMARY_BPTTLT();
            if (pgmRtn) return;
            T000_STARTBR_BR();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BR_TLR();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTTLT();
        if (pgmRtn) return;
        while (WS_FRZ_FLG != 'N' 
            && WS_I <= 20) {
            B060_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            T000_READNEXT_BPTTLT();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTTLT();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTLT() throws IOException,SQLException,Exception {
        BPRTLT.TLR_BR = BPB7993_AWA_7993.TLR_BR;
        BPRTLT.KEY.TLR = BPB7993_AWA_7993.STR_POS;
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        BPTTLT_RD.where = "TLR_BR = :BPRTLT.TLR_BR "
            + "AND TLR = :BPRTLT.KEY.TLR";
        BPTTLT_RD.fst = true;
        IBS.READ(SCCGWA, BPRTLT, this, BPTTLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BR() throws IOException,SQLException,Exception {
        BPRTLT.TLR_BR = BPB7993_AWA_7993.TLR_BR;
        BPTTLT_BR.rp = new DBParm();
        BPTTLT_BR.rp.TableName = "BPTTLT";
        BPTTLT_BR.rp.where = "TLR_BR = :BPRTLT.TLR_BR";
        BPTTLT_BR.rp.order = "TLR";
        IBS.STARTBR(SCCGWA, BPRTLT, this, BPTTLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BR_TLR() throws IOException,SQLException,Exception {
        BPRTLT.TLR_BR = BPB7993_AWA_7993.TLR_BR;
        BPRTLT.KEY.TLR = BPB7993_AWA_7993.STR_POS;
        BPTTLT_BR.rp = new DBParm();
        BPTTLT_BR.rp.TableName = "BPTTLT";
        BPTTLT_BR.rp.where = "TLR_BR = :BPRTLT.TLR_BR "
            + "AND TLR > :BPRTLT.KEY.TLR";
        BPTTLT_BR.rp.order = "TLR";
        IBS.STARTBR(SCCGWA, BPRTLT, this, BPTTLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTTLT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        IBS.READNEXT(SCCGWA, BPRTLT, this, BPTTLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
            WS_I += 1;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTTLT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTLT_BR);
    }
    public void T000_SUMMARY_BPTTLT() throws IOException,SQLException,Exception {
        BPRTLT.TLR_BR = BPB7993_AWA_7993.TLR_BR;
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        BPTTLT_RD.set = "WS-TOT-CNT=COUNT(*)";
        BPTTLT_RD.where = "TLR_BR = :BPRTLT.TLR_BR";
        IBS.GROUP(SCCGWA, BPRTLT, this, BPTTLT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_TOT_CNT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "SUMMARY BPTTLT ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
