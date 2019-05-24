package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBORR {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRW_ORG_REL = "BP-R-BRW-ORG-REL    ";
    int K_MAX_CNT = 50;
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSBORR_WS_SORRB_HEAD WS_SORRB_HEAD = new BPZSBORR_WS_SORRB_HEAD();
    BPZSBORR_WS_SORRB_DETAIL WS_SORRB_DETAIL = new BPZSBORR_WS_SORRB_DETAIL();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRORGR BPRORGR = new BPRORGR();
    BPCRBORR BPCRBORR = new BPCRBORR();
    SCCGWA SCCGWA;
    BPCSBORR BPCSBORR;
    public void MP(SCCGWA SCCGWA, BPCSBORR BPCSBORR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBORR = BPCSBORR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBORR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_BRAWSE_ORGR_RECORD();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCSBORR.BNK.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BRAWSE_ORGR_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBORR);
        IBS.init(SCCGWA, BPRORGR);
        BPRORGR.KEY.BNK = BPCSBORR.BNK;
        if (BPCSBORR.BR != 0) {
            BPRORGR.KEY.BR = BPCSBORR.BR;
        }
        if (BPCSBORR.TYP.trim().length() > 0) {
            BPRORGR.KEY.TYP = BPCSBORR.TYP;
        }
        BPCRBORR.INFO.POINTER = BPRORGR;
        BPCRBORR.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCRBORR.RC);
        BPCRBORR.FUNC = 'S';
        S000_CALL_BPZRBORR();
        if (pgmRtn) return;
        B021_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRBORR.RC.RC_CODE == 0 
            && WS_CNT < K_MAX_CNT 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            BPCRBORR.RETURN_INFO = ' ';
            IBS.init(SCCGWA, BPCRBORR.RC);
            BPCRBORR.FUNC = 'R';
            S000_CALL_BPZRBORR();
            if (pgmRtn) return;
            if (BPCRBORR.RC.RC_CODE == 0) {
                B023_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
        }
        BPCRBORR.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCRBORR.RC);
        BPCRBORR.FUNC = 'E';
        S000_CALL_BPZRBORR();
        if (pgmRtn) return;
    }
    public void B021_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 170;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 9;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B022_OUTPUT_HEADLINE() throws IOException,SQLException,Exception {
        WS_SORRB_HEAD.WS_SORRB_BNK = BPCSBORR.BNK;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_SORRB_HEAD);
        SCCMPAG.DATA_LEN = 4;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B023_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_SORRB_DETAIL);
        WS_SORRB_DETAIL.WS_SORRB_TYP = BPRORGR.KEY.TYP;
        WS_SORRB_DETAIL.WS_SORRB_BR = BPRORGR.KEY.BR;
        WS_SORRB_DETAIL.WS_SORRB_REL_BR = BPRORGR.REL_BR;
        WS_SORRB_DETAIL.WS_SORRB_EFF_DT = BPRORGR.EFF_DT;
        WS_SORRB_DETAIL.WS_SORRB_EXP_DT = BPRORGR.EXP_DT;
        WS_SORRB_DETAIL.WS_SORRB_REMARK = BPRORGR.REMARK;
        WS_SORRB_DETAIL.WS_SORRB_FILLER = 0X02;
        WS_SORRB_DETAIL.WS_SORRB_UPT_DT = BPRORGR.UPT_DT;
        WS_SORRB_DETAIL.WS_SORRB_UPT_TLR = BPRORGR.UPT_TLR;
        WS_SORRB_DETAIL.WS_SORRB_BK = BPCSBORR.BNK;
        CEP.TRC(SCCGWA, WS_SORRB_DETAIL.WS_SORRB_BK);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_SORRB_DETAIL);
        SCCMPAG.DATA_LEN = 170;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRBORR() throws IOException,SQLException,Exception {
        BPCRBORR.INFO.LEN = 169;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_ORG_REL, BPCRBORR);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCRBORR.RC);
        if (BPCRBORR.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORGREL_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBORR.RC);
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
