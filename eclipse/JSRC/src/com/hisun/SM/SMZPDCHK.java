package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class SMZPDCHK {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short K_ITM_CNT = 48;
    String CPN_DCHK_MAINTAIN = "SM-R_DCHK_MAINTAIN  ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    char WS_CHECKLIST_FLAG = ' ';
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SMCTCHKM SMCTCHKM = new SMCTCHKM();
    BPRDCHK BPRDCHK = new BPRDCHK();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SMCPDCHK SMCPDCHK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, SMCPDCHK SMCPDCHK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCPDCHK = SMCPDCHK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZPDCHK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, BPRDCHK);
        IBS.init(SCCGWA, SMCTCHKM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQUIRE_PROCESS();
        if (pgmRtn) return;
        B030_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (SMCPDCHK.NAME.trim().length() == 0 
            || SMCPDCHK.FMTNO.trim().length() == 0 
            || SMCPDCHK.ITM_DATA.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_FIELD_MUSTINPUT, SMCPDCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQUIRE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDCHK);
        CEP.TRC(SCCGWA, SMCPDCHK.NAME);
        CEP.TRC(SCCGWA, SMCPDCHK.FMTNO);
        BPRDCHK.KEY.NAME = SMCPDCHK.NAME;
        BPRDCHK.KEY.FMT = SMCPDCHK.FMTNO;
        SMCTCHKM.INFO.FUNC = 'Q';
        SMCTCHKM.INFO.POINTER = BPRDCHK;
        SMCTCHKM.INFO.LEN = 122;
        S000_CALL_SMZTCHKM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRDCHK);
        if (SMCTCHKM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_DCHK_NOTFND, SMCPDCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, K_ITM_CNT);
        WS_CHECKLIST_FLAG = 'N';
        for (WS_CNT = 1; WS_CNT <= K_ITM_CNT 
            && WS_CHECKLIST_FLAG != 'Y'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPRDCHK.REDEFINES15.REDEFINES17.ITM_TEXT[WS_CNT-1].ITM_DATA);
            if (BPRDCHK.REDEFINES15.REDEFINES17.ITM_TEXT[WS_CNT-1].ITM_DATA.equalsIgnoreCase(SMCPDCHK.ITM_DATA)) {
                WS_CHECKLIST_FLAG = 'Y';
                if (BPCRBANK.COUN_CD.equalsIgnoreCase("CN")) {
                    SMCPDCHK.ITM_RMK = BPRDCHK.REDEFINES15.REDEFINES17.ITM_TEXT[WS_CNT-1].ITM_CRMK;
                } else {
                    SMCPDCHK.ITM_RMK = BPRDCHK.REDEFINES15.REDEFINES17.ITM_TEXT[WS_CNT-1].ITM_RMK;
                }
            }
        }
        if (WS_CHECKLIST_FLAG == 'N') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_RECORD_NOTFND, SMCPDCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SMZTCHKM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCHK_MAINTAIN, SMCTCHKM);
        CEP.TRC(SCCGWA, BPRDCHK);
        if (SMCTCHKM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCTCHKM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCPDCHK.RC);
            Z_RET();
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
