package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCTIQ {
    DBParm DCTCDDAT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CIZSSCH = "CI-SEARCH-CI";
    String OUTPUT_FMT = "DC414";
    DCZSCTIQ_WS_VARIABLES WS_VARIABLES = new DCZSCTIQ_WS_VARIABLES();
    DCZSCTIQ_WS_OUTPUT WS_OUTPUT = new DCZSCTIQ_WS_OUTPUT();
    DCZSCTIQ_WS_COND_FLG WS_COND_FLG = new DCZSCTIQ_WS_COND_FLG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    CICSSCH CICSSCH = new CICSSCH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCS4414 DCCS4414;
    public void MP(SCCGWA SCCGWA, DCCS4414 DCCS4414) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS4414 = DCCS4414;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCTIQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_OUTPUT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CI_NO();
        if (pgmRtn) return;
        B030_GET_CARD_INFORMATION();
        if (pgmRtn) return;
        B040_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS4414.ID_TYPE);
        CEP.TRC(SCCGWA, DCCS4414.ID_NO);
        CEP.TRC(SCCGWA, DCCS4414.CI_NAME);
        if (DCCS4414.ID_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_MUST_INPUT_ID_TYPE);
        }
        if (DCCS4414.ID_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_MUST_INPUT_ID_NO);
        }
        if (DCCS4414.CI_NAME.trim().length() == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_MUST_INPUT_CI_NAME);
        }
    }
    public void B020_GET_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSSCH);
        CICSSCH.INPUT_DATA.I_ID_TYPE = DCCS4414.ID_TYPE;
        CICSSCH.INPUT_DATA.I_ID_NO = DCCS4414.ID_NO;
        CICSSCH.INPUT_DATA.I_CI_NM = DCCS4414.CI_NAME;
        CICSSCH.FUNC = 'B';
        S000_CALL_CIZSSCH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSSCH.OUTPUT_DATA.O_CI_NO);
    }
    public void B030_GET_CARD_INFORMATION() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.CARD_HLDR_CINO = CICSSCH.OUTPUT_DATA.O_CI_NO;
        DCRCDDAT.PROD_CD = "1203010101";
        T000_READ_DCTCDDAT_FR();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'Y') {
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
            WS_OUTPUT.O_CARD_NO = DCRCDDAT.KEY.CARD_NO;
            WS_OUTPUT.O_CARD_STS = DCRCDDAT.CARD_STS;
        } else {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_NO_RSLT);
        }
    }
    public void B040_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 20;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZSSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CIZSSCH, CICSSCH);
        if (CICSSCH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, CICSSCH.RC.RC_CODE);
            CEP.ERR(SCCGWA, CICSSCH.RC);
        }
    }
    public void T000_READ_DCTCDDAT_FR() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.col = "CARD_NO , CARD_HLDR_CINO, CARD_STS , ISSU_DT";
        DCTCDDAT_RD.where = "CARD_HLDR_CINO = :DCRCDDAT.CARD_HLDR_CINO "
            + "AND PROD_CD = :DCRCDDAT.PROD_CD "
            + "AND CARD_STS < > 'C'";
        DCTCDDAT_RD.fst = true;
        DCTCDDAT_RD.order = "ISSU_DT DESC";
        IBS.READ(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
            CEP.TRC(SCCGWA, "DCTCDDAT NOT FOUND1:");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
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
