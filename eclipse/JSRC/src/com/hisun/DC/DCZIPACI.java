package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZIPACI {
    DBParm DCTSPAC_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char WS_RETURN_INFO = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRSPAC DCRSPAC = new DCRSPAC();
    int WS_STD_AC_COUNT = 0;
    String WS_IPACI_STD_AC = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCIPACI DCCIPACI;
    public void MP(SCCGWA SCCGWA, DCCIPACI DCCIPACI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCIPACI = DCCIPACI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZIPACI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCIPACI.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_WRITE_SPAC_PROC();
        if (pgmRtn) return;
        B040_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCIPACI.STD_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_STD_AC_MUST_INPUT, DCCIPACI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCIPACI.FREE_TYPE);
        if (DCCIPACI.FREE_TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FREE_TYPE_MUST_INPUT, DCCIPACI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!DCCIPACI.FREE_TYPE.equalsIgnoreCase("001") 
            && !DCCIPACI.FREE_TYPE.equalsIgnoreCase("002") 
            && !DCCIPACI.FREE_TYPE.equalsIgnoreCase("003") 
            && !DCCIPACI.FREE_TYPE.equalsIgnoreCase("004") 
            && !DCCIPACI.FREE_TYPE.equalsIgnoreCase("005") 
            && !DCCIPACI.FREE_TYPE.equalsIgnoreCase("006") 
            && !DCCIPACI.FREE_TYPE.equalsIgnoreCase("007")) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FREE_TYPE_INVALID, DCCIPACI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_WRITE_SPAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRSPAC);
        DCRSPAC.KEY.FREE_AC = DCCIPACI.FREE_AC;
        DCRSPAC.FREE_TYPE = DCCIPACI.FREE_TYPE;
        DCRSPAC.STD_AC = DCCIPACI.STD_AC;
        DCRSPAC.EFF_FLG = '1';
        DCRSPAC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRSPAC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRSPAC.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DCRSPAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTSPAC();
        if (pgmRtn) return;
    }
    public void B040_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        DCCIPACI.FREE_AC = DCRSPAC.KEY.FREE_AC;
    }
    public void T000_WRITE_DCTSPAC() throws IOException,SQLException,Exception {
        DCTSPAC_RD = new DBParm();
        DCTSPAC_RD.TableName = "DCTSPAC";
        DCTSPAC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRSPAC, DCTSPAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "WRITE TABLE DCTSPAC ERROR!";
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
        if (DCCIPACI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCIPACI=");
            CEP.TRC(SCCGWA, DCCIPACI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
