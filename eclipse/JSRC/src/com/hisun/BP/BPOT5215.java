package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5215 {
    brParm BPTIHIS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_OUTPUT_FMT = "BPA01";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    int K_MAX_ROW = 30;
    int WS_I = 0;
    int WS_PAGE_ROW = 0;
    int WS_CURRENT_ROW = 0;
    int WS_MIN_ROW = 0;
    int WS_MAX_ROW = 0;
    int WS_RECORD_NUM = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_TENOR = " ";
    char WS_FRZ_FLG = ' ';
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    int WS_SET_DT = 0;
    int WS_SET_TM = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPCIHIS BPCIHIS = new BPCIHIS();
    BPRIHIS BPRIHIS = new BPRIHIS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPB5217_AWA_5217 BPB5217_AWA_5217;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT5215 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5217_AWA_5217>");
        BPB5217_AWA_5217 = (BPB5217_AWA_5217) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCIHIS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5217_AWA_5217.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.HQT_BANK);
        if (BPB5217_AWA_5217.BR == ' ' 
            || BPB5217_AWA_5217.BR == 0) {
            BPB5217_AWA_5217.BR = SCCGWA.COMM_AREA.HQT_BANK;
        } else {
            if (BPB5217_AWA_5217.BR != 999999) {
                WS_BR = BPB5217_AWA_5217.BR;
                R000_CHECK_BRANCH();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPB5217_AWA_5217.CCY);
        if (BPB5217_AWA_5217.CCY.trim().length() > 0) {
            WS_CCY = BPB5217_AWA_5217.CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB5217_AWA_5217.BASE_TYP);
        if (BPB5217_AWA_5217.BASE_TYP.trim().length() > 0) {
            WS_BASE_TYP = BPB5217_AWA_5217.BASE_TYP;
            R000_CHECK_BASE_TYP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB5217_AWA_5217.TENOR);
        if (BPB5217_AWA_5217.TENOR.trim().length() > 0) {
            WS_TENOR = BPB5217_AWA_5217.TENOR;
            R000_CHECK_TENOR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB5217_AWA_5217.STR_DT);
        if (BPB5217_AWA_5217.STR_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
        }
        CEP.TRC(SCCGWA, BPB5217_AWA_5217.END_DT);
        if (BPB5217_AWA_5217.END_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
        }
        CEP.TRC(SCCGWA, "CHECK IF AWA-END-DT > AWA-EFF-DT");
        if (BPB5217_AWA_5217.STR_DT > BPB5217_AWA_5217.END_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPB5217_AWA_5217.PAGE_ROW);
        if (BPB5217_AWA_5217.PAGE_ROW > K_MAX_ROW) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = BPB5217_AWA_5217.PAGE_ROW;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_COMPUTE_OUTPUT_ROW();
        if (pgmRtn) return;
        B040_INTR_INFO();
        if (pgmRtn) return;
    }
    public void B020_COMPUTE_OUTPUT_ROW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5217_AWA_5217.PAGE_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_ROW);
        WS_MIN_ROW = ( BPB5217_AWA_5217.PAGE_NUM - 1 ) * WS_PAGE_ROW + 1;
        CEP.TRC(SCCGWA, WS_MIN_ROW);
        WS_MAX_ROW = BPB5217_AWA_5217.PAGE_NUM * WS_PAGE_ROW;
        CEP.TRC(SCCGWA, WS_MAX_ROW);
        WS_CURRENT_ROW = 0;
        WS_I = 0;
    }
    public void B040_INTR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIS);
        B050_DATA_TRANS();
        if (pgmRtn) return;
        T000_STARTBR_DT();
        if (pgmRtn) return;
        T000_READNEXT_BPTIHIS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND);
        }
        while (WS_FRZ_FLG != 'N' 
            && WS_I <= 20) {
            WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
            CEP.TRC(SCCGWA, WS_CURRENT_ROW);
            if (WS_CURRENT_ROW >= WS_MIN_ROW 
                && WS_CURRENT_ROW <= WS_MAX_ROW) {
                B060_01_DATA_TRANS_TO_FMT();
                if (pgmRtn) return;
            }
            T000_READNEXT_BPTIHIS();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCIHIS.LAST_PAGE = 'Y';
        } else {
            BPCIHIS.LAST_PAGE = 'N';
        }
        T000_ENDBR_BPTIHIS();
        if (pgmRtn) return;
        B060_02_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B050_DATA_TRANS() throws IOException,SQLException,Exception {
        BPRIHIS.KEY.BR = BPB5217_AWA_5217.BR;
        BPRIHIS.KEY.CCY = BPB5217_AWA_5217.CCY;
        BPRIHIS.KEY.BASE_TYP = BPB5217_AWA_5217.BASE_TYP;
        BPRIHIS.KEY.TENOR = BPB5217_AWA_5217.TENOR;
        WS_STR_DT = BPB5217_AWA_5217.STR_DT;
        WS_END_DT = BPB5217_AWA_5217.END_DT;
    }
    public void B060_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CURRENT_ROW);
        BPCIHIS.TOTAL_NUM = WS_CURRENT_ROW;
        WS_RECORD_NUM = WS_CURRENT_ROW % WS_PAGE_ROW;
        BPCIHIS.TOTAL_PAGE = (int) ((WS_CURRENT_ROW - WS_RECORD_NUM) / WS_PAGE_ROW);
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
        if (WS_RECORD_NUM > 0) {
            BPCIHIS.TOTAL_PAGE = BPCIHIS.TOTAL_PAGE + 1;
        }
        BPCIHIS.CURR_PAGE = BPB5217_AWA_5217.PAGE_NUM;
        BPCIHIS.PAGE_ROW = WS_I;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCIHIS;
        SCCFMT.DATA_LEN = 885;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        WS_I = WS_I + 1;
        CEP.TRC(SCCGWA, WS_I);
        BPCIHIS.TIMES[WS_I-1].BR = BPRIHIS.KEY.BR;
        BPCIHIS.TIMES[WS_I-1].CCY = BPRIHIS.KEY.CCY;
        BPCIHIS.TIMES[WS_I-1].BASE_TYP = BPRIHIS.KEY.BASE_TYP;
        BPCIHIS.TIMES[WS_I-1].TENOR = BPRIHIS.KEY.TENOR;
        BPCIHIS.TIMES[WS_I-1].RATE = BPRIHIS.RATE;
        BPCIHIS.TIMES[WS_I-1].SET_DT = BPRIHIS.KEY.DT;
        BPCIHIS.TIMES[WS_I-1].SET_TM = BPRIHIS.KEY.TM;
    }
    public void T000_STARTBR_DT() throws IOException,SQLException,Exception {
        BPTIHIS_BR.rp = new DBParm();
        BPTIHIS_BR.rp.TableName = "BPTIHIS";
        BPTIHIS_BR.rp.where = "BR = :BPRIHIS.KEY.BR "
            + "AND CCY = :BPRIHIS.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIS.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIS.KEY.TENOR "
            + "AND DT >= :WS_STR_DT "
            + "AND DT <= :WS_END_DT";
        BPTIHIS_BR.rp.order = "DT,TM";
        IBS.STARTBR(SCCGWA, BPRIHIS, this, BPTIHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTIHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DT_NEW() throws IOException,SQLException,Exception {
        BPTIHIS_BR.rp = new DBParm();
        BPTIHIS_BR.rp.TableName = "BPTIHIS";
        BPTIHIS_BR.rp.where = "BR = :BPRIHIS.KEY.BR "
            + "AND CCY = :BPRIHIS.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIS.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIS.KEY.TENOR "
            + "AND ( ( DT = :WS_SET_DT "
            + "AND TM > :WS_SET_TM ) "
            + "OR ( DT > :WS_SET_DT ) ) "
            + "AND DT <= :WS_END_DT";
        BPTIHIS_BR.rp.order = "DT,TM";
        IBS.STARTBR(SCCGWA, BPRIHIS, this, BPTIHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTIHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTIHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        IBS.READNEXT(SCCGWA, BPRIHIS, this, BPTIHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTIHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTIHIS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTIHIS_BR);
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB5217_AWA_5217.BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY);
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            WS_FLD_NO = BPB5217_AWA_5217.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_BASE_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RBASE;
        BPCOQPCD.INPUT_DATA.CODE = WS_BASE_TYP;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            WS_FLD_NO = BPB5217_AWA_5217.BASE_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RTENO;
        BPCOQPCD.INPUT_DATA.CODE = WS_TENOR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            WS_FLD_NO = BPB5217_AWA_5217.BASE_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
