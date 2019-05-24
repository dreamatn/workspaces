package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPMDAY {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CMP_MAIN_BPTDAYE = "BP-R-MAINT-DAYE";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCTDAYE BPCTDAYE = new BPCTDAYE();
    BPRDAYE BPRDAYE = new BPRDAYE();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCPMDAY BPCPMDAY;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPMDAY BPCPMDAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPMDAY = BPCPMDAY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPMDAY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPMDAY);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPMDAY.RC.FUNC);
        CEP.TRC(SCCGWA, BPCPMDAY.TYPE);
        CEP.TRC(SCCGWA, BPCPMDAY.AREA);
        CEP.TRC(SCCGWA, BPCPMDAY.BR);
        CEP.TRC(SCCGWA, BPCPMDAY.DATE);
        CEP.TRC(SCCGWA, BPCPMDAY.CHARACTER);
        CEP.TRC(SCCGWA, BPCPMDAY.CHECK_RUSULT);
        if (((BPCPMDAY.RC.FUNC != 'A' 
            && BPCPMDAY.RC.FUNC != 'I' 
            && BPCPMDAY.RC.FUNC != 'M' 
            && BPCPMDAY.RC.FUNC != 'D' 
            && BPCPMDAY.RC.FUNC != 'C')) 
            || ((BPCPMDAY.TYPE != 'A' 
            && BPCPMDAY.TYPE != 'B' 
            && BPCPMDAY.TYPE != 'I'))) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPMDAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((BPCPMDAY.TYPE == 'A' 
            && (BPCPMDAY.AREA.equalsIgnoreCase("0") 
            || BPCPMDAY.AREA.trim().length() == 0)) 
            || (BPCPMDAY.TYPE == 'B' 
            && BPCPMDAY.BR == 0)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPMDAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPMDAY.DATE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPMDAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((BPCPMDAY.RC.FUNC == 'A') 
            && ((!BPCPMDAY.CHARACTER.equalsIgnoreCase("01") 
            && !BPCPMDAY.CHARACTER.equalsIgnoreCase("02") 
            && !BPCPMDAY.CHARACTER.equalsIgnoreCase("03") 
            && !BPCPMDAY.CHARACTER.equalsIgnoreCase("04")))) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPMDAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (BPCPMDAY.RC.FUNC == 'A') {
            B210_ADD_PROC();
            if (pgmRtn) return;
        } else if (BPCPMDAY.RC.FUNC == 'I') {
            B220_INQ_PROC();
            if (pgmRtn) return;
        } else if (BPCPMDAY.RC.FUNC == 'M') {
            B230_MOD_PROC();
            if (pgmRtn) return;
        } else if (BPCPMDAY.RC.FUNC == 'D') {
            B240_DEL_PROC();
            if (pgmRtn) return;
        } else if (BPCPMDAY.RC.FUNC == 'C') {
            B250_CHK_PROC();
            if (pgmRtn) return;
        }
    }
    public void B210_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDAYE);
        IBS.init(SCCGWA, BPCTDAYE);
        BPRDAYE.KEY.TYPE = BPCPMDAY.TYPE;
        BPRDAYE.KEY.RGN = BPCPMDAY.AREA;
        BPRDAYE.KEY.BCH = "" + BPCPMDAY.BR;
        JIBS_tmp_int = BPRDAYE.KEY.BCH.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRDAYE.KEY.BCH = "0" + BPRDAYE.KEY.BCH;
        BPRDAYE.KEY.DATE = BPCPMDAY.DATE;
        BPRDAYE.CHARACTER = BPCPMDAY.CHARACTER;
        BPRDAYE.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRDAYE.LAST_TELLER = SCCGWA.COMM_AREA.TL_ID;
        BPCTDAYE.INFO.FUNC = 'C';
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
        if (BPCTDAYE.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DAYE_EXIST, BPCPMDAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B220_INQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDAYE);
        IBS.init(SCCGWA, BPCTDAYE);
        BPRDAYE.KEY.TYPE = BPCPMDAY.TYPE;
        BPRDAYE.KEY.RGN = BPCPMDAY.AREA;
        BPRDAYE.KEY.BCH = "" + BPCPMDAY.BR;
        JIBS_tmp_int = BPRDAYE.KEY.BCH.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRDAYE.KEY.BCH = "0" + BPRDAYE.KEY.BCH;
        BPRDAYE.KEY.DATE = BPCPMDAY.DATE;
        BPCTDAYE.INFO.FUNC = 'Q';
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
        if (BPCTDAYE.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DAYE_NOTFND, BPCPMDAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCPMDAY.CHARACTER = BPRDAYE.CHARACTER;
        BPCPMDAY.LAST_DATE = BPRDAYE.LAST_DATE;
        BPCPMDAY.LAST_TELLER = BPRDAYE.LAST_TELLER;
    }
    public void B230_MOD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDAYE);
        IBS.init(SCCGWA, BPCTDAYE);
        BPRDAYE.KEY.TYPE = BPCPMDAY.TYPE;
        BPRDAYE.KEY.RGN = BPCPMDAY.AREA;
        BPRDAYE.KEY.BCH = "" + BPCPMDAY.BR;
        JIBS_tmp_int = BPRDAYE.KEY.BCH.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRDAYE.KEY.BCH = "0" + BPRDAYE.KEY.BCH;
        BPRDAYE.KEY.DATE = BPCPMDAY.DATE;
        BPCTDAYE.INFO.FUNC = 'R';
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
        if (BPCTDAYE.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DAYE_NOTFND, BPCPMDAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPMDAY.CHARACTER);
        BPRDAYE.CHARACTER = BPCPMDAY.CHARACTER;
        BPRDAYE.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRDAYE.LAST_TELLER = SCCGWA.COMM_AREA.TL_ID;
        BPCTDAYE.INFO.FUNC = 'U';
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
    }
    public void B240_DEL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDAYE);
        IBS.init(SCCGWA, BPCTDAYE);
        BPRDAYE.KEY.TYPE = BPCPMDAY.TYPE;
        BPRDAYE.KEY.RGN = BPCPMDAY.AREA;
        BPRDAYE.KEY.BCH = "" + BPCPMDAY.BR;
        JIBS_tmp_int = BPRDAYE.KEY.BCH.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRDAYE.KEY.BCH = "0" + BPRDAYE.KEY.BCH;
        BPRDAYE.KEY.DATE = BPCPMDAY.DATE;
        BPCTDAYE.INFO.FUNC = 'R';
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
        if (BPCTDAYE.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DAYE_NOTFND, BPCPMDAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCTDAYE.INFO.FUNC = 'D';
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
    }
    public void B250_CHK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDAYE);
        IBS.init(SCCGWA, BPCTDAYE);
        BPRDAYE.KEY.TYPE = BPCPMDAY.TYPE;
        BPRDAYE.KEY.RGN = BPCPMDAY.AREA;
        BPRDAYE.KEY.BCH = "" + BPCPMDAY.BR;
        JIBS_tmp_int = BPRDAYE.KEY.BCH.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRDAYE.KEY.BCH = "0" + BPRDAYE.KEY.BCH;
        BPRDAYE.KEY.DATE = BPCPMDAY.DATE;
        BPCTDAYE.INFO.FUNC = 'Q';
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
        if (BPCTDAYE.RETURN_INFO == 'N') {
            BPCPMDAY.CHECK_RUSULT = 'N';
        } else {
            BPCPMDAY.CHECK_RUSULT = 'E';
        }
    }
    public void S000_CALL_BPZTDAYE() throws IOException,SQLException,Exception {
        BPCTDAYE.INFO.POINTER = BPRDAYE;
        IBS.CALLCPN(SCCGWA, K_CMP_MAIN_BPTDAYE, BPCTDAYE);
        if (BPCTDAYE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTDAYE.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPMDAY.RC);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPMDAY.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPMDAY = ");
            CEP.TRC(SCCGWA, BPCPMDAY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
