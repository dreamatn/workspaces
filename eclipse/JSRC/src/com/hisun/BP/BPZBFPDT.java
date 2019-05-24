package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZBFPDT {
    DBParm BPTFPDT_RD;
    BPCOFPDT_DATA DATA;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_X = "BP056";
    int K_MAX_ROW = 50;
    String CPN_R_FPDT = "BP-F-R-FE-UNCHG-DTL";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFPDT BPRFPDT = new BPRFPDT();
    BPCRFPDT BPCRFPDT = new BPCRFPDT();
    BPCOFPDT BPCOFPDT = new BPCOFPDT();
    String WS_ERR_MSG = " ";
    short WS_REMAIN = 0;
    int WS_RD_NUM = 0;
    int WS_COUNT_NO = 0;
    int WS_RCD_SEQ = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGMSG SCCGMSG;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCBFPDT BPCBFPDT;
    public void MP(SCCGWA SCCGWA, BPCBFPDT BPCBFPDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCBFPDT = BPCBFPDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZBFPDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_COUNT_REC_NUM();
        if (pgmRtn) return;
        B310_OUT_TITLE_RTN();
        if (pgmRtn) return;
        B300_BROWSER_PROCESS();
        if (pgmRtn) return;
        B500_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_COUNT_REC_NUM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCBFPDT.BSNS_NO);
        CEP.TRC(SCCGWA, BPCBFPDT.FEE_CODE);
        if (BPCBFPDT.FEE_CODE.trim().length() == 0) {
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFPDT_RD = new DBParm();
            BPTFPDT_RD.TableName = "BPTFPDT";
            BPTFPDT_RD.where = "BSNS_NO = :BPCBFPDT.BSNS_NO";
            IBS.GROUP(SCCGWA, BPRFPDT, this, BPTFPDT_RD);
        } else {
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFPDT_RD = new DBParm();
            BPTFPDT_RD.TableName = "BPTFPDT";
            BPTFPDT_RD.where = "BSNS_NO = :BPCBFPDT.BSNS_NO "
                + "AND FEE_CODE = :BPCBFPDT.FEE_CODE";
            IBS.GROUP(SCCGWA, BPRFPDT, this, BPTFPDT_RD);
        }
        CEP.TRC(SCCGWA, WS_COUNT_NO);
    }
    public void B300_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFPDT);
        BPRFPDT.BSNS_NO = BPCBFPDT.BSNS_NO;
        BPRFPDT.FEE_CODE = BPCBFPDT.FEE_CODE;
        BPCRFPDT.INFO.FUNC = '7';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFPDT);
        BPCRFPDT.INFO.READ_SEQ = BPCOFPDT.OUTPUT_TITLE.CURR_PAGE_ROW * ( BPCOFPDT.OUTPUT_TITLE.CURR_PAGE - 1 ) + 1;
        CEP.TRC(SCCGWA, BPCRFPDT.INFO.READ_SEQ);
        BPCRFPDT.INFO.FUNC = 'S';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        WS_RD_NUM = 0;
        while (BPCRFPDT.RETURN_INFO != 'N' 
            && WS_RD_NUM < BPCOFPDT.OUTPUT_TITLE.CURR_PAGE_ROW) {
            if (BPRFPDT.CHG_STS == '0' 
                || BPRFPDT.CHG_STS == '1') {
                WS_RD_NUM += 1;
                B320_OUT_LINE_DATA();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_RD_NUM);
            IBS.init(SCCGWA, BPCRFPDT);
            IBS.init(SCCGWA, BPRFPDT);
            BPCRFPDT.INFO.FUNC = 'N';
            S000_CALL_BPZRFPDT();
            if (pgmRtn) return;
        }
        BPCRFPDT.INFO.FUNC = 'E';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
    }
    public void B310_OUT_TITLE_RTN() throws IOException,SQLException,Exception {
        BPCOFPDT.OUTPUT_TITLE.CURR_PAGE = BPCBFPDT.PAGE_NUM;
        BPCOFPDT.OUTPUT_TITLE.CURR_PAGE_ROW = BPCBFPDT.PAGE_ROW;
        DATA = new BPCOFPDT_DATA();
        BPCOFPDT.DATA.add(DATA);
        BPCOFPDT.TOTAL_DATA.TOTAL_NUM = WS_COUNT_NO;
        WS_REMAIN = (short) (BPCOFPDT.TOTAL_DATA.TOTAL_NUM % BPCOFPDT.OUTPUT_TITLE.CURR_PAGE_ROW);
        BPCOFPDT.TOTAL_DATA.TOTAL_PAGE = (int) ((BPCOFPDT.TOTAL_DATA.TOTAL_NUM - WS_REMAIN) / BPCOFPDT.OUTPUT_TITLE.CURR_PAGE_ROW);
        if (WS_REMAIN > 0) {
            BPCOFPDT.TOTAL_DATA.TOTAL_PAGE += 1;
        }
        if (BPCOFPDT.OUTPUT_TITLE.CURR_PAGE > BPCOFPDT.TOTAL_DATA.TOTAL_PAGE) {
            BPCOFPDT.OUTPUT_TITLE.CURR_PAGE = BPCOFPDT.TOTAL_DATA.TOTAL_PAGE;
        }
        if (BPCOFPDT.OUTPUT_TITLE.CURR_PAGE == BPCOFPDT.TOTAL_DATA.TOTAL_PAGE) {
            BPCOFPDT.OUTPUT_TITLE.CURR_PAGE_ROW = WS_REMAIN;
            DATA = new BPCOFPDT_DATA();
            BPCOFPDT.DATA.add(DATA);
            BPCOFPDT.OUTPUT_TITLE.LAST_FLG = 'Y';
        } else {
            BPCOFPDT.OUTPUT_TITLE.LAST_FLG = 'N';
        }
    }
    public void B320_OUT_LINE_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RD_NUM);
        DATA.TRT_DT = BPRFPDT.KEY.TRT_DT;
        if (BPRFPDT.KEY.JRN_NO.trim().length() == 0) DATA.JRN_NO = 0;
        else DATA.JRN_NO = Long.parseLong(BPRFPDT.KEY.JRN_NO);
        DATA.JRN_SEQ = BPRFPDT.KEY.JRN_SEQ;
        DATA.CHG_CARD = BPRFPDT.CARD_PSBK_NO;
        DATA.TX_CI = BPRFPDT.TX_CI;
        DATA.CHG_AC_TY = BPRFPDT.CHG_AC_TY;
        DATA.CHG_AC = BPRFPDT.CHG_AC;
        DATA.FEE_SRC = BPRFPDT.FEE_SRC;
        DATA.FEE_CODE = BPRFPDT.FEE_CODE;
        DATA.CCY = BPRFPDT.CCY;
        DATA.CCY_TYPE = BPRFPDT.CCY_TYPE;
        DATA.CHG_BR = BPRFPDT.CHG_BR;
        DATA.CHG_AMT = BPRFPDT.CHG_AMT;
        DATA.ACC_CNT = BPRFPDT.ACC_RECH_CNT;
        DATA.CUR_OWE_AMT = BPRFPDT.CUR_OWE_AMT;
        DATA.ACC_CHG_AMT = BPRFPDT.ACC_CHG_AMT;
        DATA.CMMT_NO = BPRFPDT.CMMT_NO;
        DATA.BSNS_NO = BPRFPDT.BSNS_NO;
        DATA.TRT_BR = BPRFPDT.TRT_BR;
        DATA.CHG_STS = BPRFPDT.CHG_STS;
        DATA.REMARK = BPRFPDT.REMARK;
    }
    public void B500_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCOFPDT.OUTPUT_TITLE.CURR_PAGE_ROW = WS_RD_NUM;
        DATA = new BPCOFPDT_DATA();
        BPCOFPDT.DATA.add(DATA);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        SCCFMT.DATA_PTR = BPCOFPDT;
        SCCFMT.DATA_LEN = 17422;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRFPDT() throws IOException,SQLException,Exception {
        BPCRFPDT.INFO.POINTER = BPRFPDT;
        BPCRFPDT.INFO.LEN = 558;
        IBS.CALLCPN(SCCGWA, CPN_R_FPDT, BPCRFPDT);
        if (BPCRFPDT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFPDT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
