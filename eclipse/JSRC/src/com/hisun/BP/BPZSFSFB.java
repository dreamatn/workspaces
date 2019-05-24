package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSFSFB {
    DBParm BPTFSAF_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRW_THIS = "BP-R-BRW-THIS ";
    String K_TBL_TLT = "BPTFSAF ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSFSFB_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSFSFB_WS_OUTPUT_DATA();
    char WS_OUTPUT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCRFSFB BPCRFSFB = new BPCRFSFB();
    BPRFSAF BPRFSAF = new BPRFSAF();
    SCCGWA SCCGWA;
    BPCSFSFB BPCSFSFB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSFSFB BPCSFSFB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSFSFB = BPCSFSFB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSFSFB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCRFSFB);
        IBS.init(SCCGWA, BPCSFSFB.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSFSFB.INFO.FUNC == 'B') {
            B010_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSFSFB.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSFSFB.INFO.FUNC == 'A') {
            B010_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFSFB);
        if (BPCSFSFB.CI_NO.trim().length() == 0) {
            BPCRFSFB.CI_NO = "%%%%%%%%%%%%";
        } else {
            BPCRFSFB.CI_NO = BPCSFSFB.CI_NO;
        }
        if (BPCSFSFB.STS == ' ') {
            BPCRFSFB.STS = ALL.charAt(0);
        } else {
            BPCRFSFB.STS = BPCSFSFB.STS;
        }
        BPCRFSFB.INFO.FUNC = '1';
        BPCRFSFB.INFO.POINTER = BPCRFSFB;
        BPCRFSFB.INFO.DATA_LEN = 566;
        S000_CALL_BPZRFSFB();
        if (pgmRtn) return;
        BPCRFSFB.INFO.FUNC = '2';
        BPCRFSFB.INFO.POINTER = BPCRFSFB;
        BPCRFSFB.INFO.DATA_LEN = 566;
        S000_CALL_BPZRFSFB();
        if (pgmRtn) return;
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRFSFB.RETURN_INFO != 'N' 
            && WS_CNT <= 5000 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            B010_03_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCRFSFB.INFO.FUNC = '2';
            S000_CALL_BPZRFSFB();
            if (pgmRtn) return;
        }
        BPCRFSFB.INFO.FUNC = '3';
        BPCRFSFB.INFO.POINTER = BPCRFSFB;
        BPCRFSFB.INFO.DATA_LEN = 566;
        S000_CALL_BPZRFSFB();
        if (pgmRtn) return;
    }
    public void B010_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 521;
        SCCMPAG.SCR_ROW_CNT = 99;
        SCCMPAG.SCR_COL_CNT = 4;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_OUTPUT_DATA.WS_TRN_DATE = BPCRFSFB.TRN_DATE;
        WS_OUTPUT_DATA.WS_JRNNO = BPCRFSFB.JRNNO;
        WS_OUTPUT_DATA.WS_CI_NO = BPCRFSFB.CI_NO;
        WS_OUTPUT_DATA.WS_CI_CNAME = BPCRFSFB.CI_CNAME;
        WS_OUTPUT_DATA.WS_CI_ENAME = BPCRFSFB.CI_ENAME;
        WS_OUTPUT_DATA.WS_AMOUNT = BPCRFSFB.AMOUNT;
        WS_OUTPUT_DATA.WS_CCY = BPCRFSFB.CCY;
        WS_OUTPUT_DATA.WS_CHG_DATE = BPCRFSFB.CHG_DATE;
        WS_OUTPUT_DATA.WS_INSURE_TYPE = BPCRFSFB.INSURE_TYPE;
        WS_OUTPUT_DATA.WS_CLEAR_DATE = BPCRFSFB.CLEAR_DATE;
        WS_OUTPUT_DATA.WS_STS = BPCRFSFB.STS;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 521;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_BPTFSAF();
        if (pgmRtn) return;
    }
    public void B010_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTFSAF();
        if (pgmRtn) return;
    }
    public void T000_QUERY_BPTFSAF() throws IOException,SQLException,Exception {
        BPTFSAF_RD = new DBParm();
        BPTFSAF_RD.TableName = "BPTFSAF";
        BPTFSAF_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRFSAF, BPTFSAF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSFSFB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCSFSFB.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTFND, BPCSFSFB.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTFSAF() throws IOException,SQLException,Exception {
        BPTFSAF_RD = new DBParm();
        BPTFSAF_RD.TableName = "BPTFSAF";
        BPTFSAF_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFSAF, BPTFSAF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSFSFB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCSFSFB.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY, BPCSFSFB.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZRFSFB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_THIS, BPCRFSFB);
        if (BPCRFSFB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFSFB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
