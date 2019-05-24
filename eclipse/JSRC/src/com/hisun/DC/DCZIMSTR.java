package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZIMSTR {
    DBParm DCTIAMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRIAMST DCRIAMST = new DCRIAMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCIMSTR DCCIMSTR;
    public void MP(SCCGWA SCCGWA, DCCIMSTR DCCIMSTR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCIMSTR = DCCIMSTR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZIMSTR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCIMSTR.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_MST_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCIMSTR.KEY.VIA_AC);
        if (DCCIMSTR.KEY.VIA_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT, DCCIMSTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_MST_DATA() throws IOException,SQLException,Exception {
        T000_READ_BY_VIA_AC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRIAMST);
        DCCIMSTR.DATA.VIA_FLG = DCRIAMST.VIA_FLG;
        DCCIMSTR.DATA.PRD_TYPE = DCRIAMST.PRD_TYPE;
        DCCIMSTR.DATA.PRD_CODE = DCRIAMST.PRD_CODE;
        DCCIMSTR.DATA.AC_STS = DCRIAMST.AC_STS;
        DCCIMSTR.DATA.STS_WORD = DCRIAMST.STS_WORD;
        DCCIMSTR.DATA.PRT_DTL_FLG = DCRIAMST.PRT_DTL_FLG;
        DCCIMSTR.DATA.CURR_POS = DCRIAMST.CURR_POS;
        DCCIMSTR.DATA.VAL_NUM = DCRIAMST.VAL_NUM;
        DCCIMSTR.DATA.PROC_NUM = DCRIAMST.PROC_NUM;
        DCCIMSTR.DATA.OPEN_DATE = DCRIAMST.OPEN_DATE;
        DCCIMSTR.DATA.OPEN_BR = DCRIAMST.OPEN_BR;
        DCCIMSTR.DATA.OPEN_TLR = DCRIAMST.OPEN_TLR;
        DCCIMSTR.DATA.CLOSE_DATE = DCRIAMST.CLOSE_DATE;
        DCCIMSTR.DATA.CLOSE_BR = DCRIAMST.CLOSE_BR;
        DCCIMSTR.DATA.CLOSE_TLR = DCRIAMST.CLOSE_TLR;
        DCCIMSTR.DATA.REOPEN_DATE = DCRIAMST.REOPEN_DATE;
        DCCIMSTR.DATA.CRT_DATE = DCRIAMST.CRT_DATE;
        DCCIMSTR.DATA.CRT_TLR = DCRIAMST.CRT_TLR;
        DCCIMSTR.DATA.UPDTBL_DATE = DCRIAMST.UPDTBL_DATE;
        DCCIMSTR.DATA.UPDTBL_TLR = DCRIAMST.UPDTBL_TLR;
        DCCIMSTR.DATA.TS = DCRIAMST.TS;
    }
    public void T000_READ_BY_VIA_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAMST);
        IBS.init(SCCGWA, DCCIMSTR.DATA);
        DCRIAMST.KEY.VIA_AC = DCCIMSTR.KEY.VIA_AC;
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        IBS.READ(SCCGWA, DCRIAMST, DCTIAMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_MST_RCD_NOT_FND, DCCIMSTR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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
        if (DCCIMSTR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCIMSTR=");
            CEP.TRC(SCCGWA, DCCIMSTR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
