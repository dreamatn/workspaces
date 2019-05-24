package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZIPACR {
    DBParm DCTSPAC_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRSPAC DCRSPAC = new DCRSPAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCIPACR DCCIPACR;
    public void MP(SCCGWA SCCGWA, DCCIPACR DCCIPACR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCIPACR = DCCIPACR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZIPACR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCIPACR.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_PAC_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCIPACR.KEY.FREE_AC);
        if (DCCIPACR.KEY.FREE_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FREE_AC_MUST_INPUT, DCCIPACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_PAC_DATA() throws IOException,SQLException,Exception {
        T000_READ_BY_FREE_AC_TYPE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRSPAC);
        DCCIPACR.KEY.FREE_TYPE = DCRSPAC.FREE_TYPE;
        DCCIPACR.DATA.STD_AC = DCRSPAC.STD_AC;
        DCCIPACR.DATA.EFF_FLG = DCRSPAC.EFF_FLG;
        DCCIPACR.DATA.CRT_DATE = DCRSPAC.CRT_DATE;
        DCCIPACR.DATA.CRT_TLR = DCRSPAC.CRT_TLR;
        DCCIPACR.DATA.UPDTBL_DATE = DCRSPAC.UPDTBL_DATE;
        DCCIPACR.DATA.UPDTBL_TLR = DCRSPAC.UPDTBL_TLR;
        DCCIPACR.DATA.TS = DCRSPAC.TS;
    }
    public void T000_READ_BY_FREE_AC_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRSPAC);
        IBS.init(SCCGWA, DCCIPACR.DATA);
        DCRSPAC.KEY.FREE_AC = DCCIPACR.KEY.FREE_AC;
        DCTSPAC_RD = new DBParm();
        DCTSPAC_RD.TableName = "DCTSPAC";
        IBS.READ(SCCGWA, DCRSPAC, DCTSPAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FREE_AC_NOT_FND, DCCIPACR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTSPAC";
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
        if (DCCIPACR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCIPACR=");
            CEP.TRC(SCCGWA, DCCIPACR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
