package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUBRRC {
    DBParm DCTCDDAT_RD;
    DBParm DCTBRARC_RD;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_BR_NO = 0;
    short WS_AREA_NO = 0;
    short WS_AREA_NO1 = 0;
    DCZUBRRC_WS_OUTPUT_VAL WS_OUTPUT_VAL = new DCZUBRRC_WS_OUTPUT_VAL();
    char WS_TBL_FLAG = ' ';
    char WS_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRBRARC DCRBRARC = new DCRBRARC();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCSMPRD BPCSMPRD = new BPCSMPRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUBRRC DCCUBRRC;
    public void MP(SCCGWA SCCGWA, DCCUBRRC DCCUBRRC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUBRRC = DCCUBRRC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCZUBRRC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCRBRARC);
        IBS.init(SCCGWA, DCRCDDAT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUBRRC);
        B001_CHECK_INPUT();
        if (DCCUBRRC.INP_DATA.FUNC == '0') {
            B002_GET_BRANCH();
            B003_GET_AREACODE();
        } else if (DCCUBRRC.INP_DATA.FUNC == '1') {
            WS_FLG = '0';
            B003_GET_AREACODE();
        } else if (DCCUBRRC.INP_DATA.FUNC == '2') {
            B002_GET_BRANCH();
            B003_GET_AREACODE();
            WS_FLG = '0';
            B003_GET_AREACODE();
        } else {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_INVALD);
        }
        B004_DATA_OUTPUT();
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUBRRC.INP_DATA.CARD_NO);
        CEP.TRC(SCCGWA, DCCUBRRC.INP_DATA.BR_NO);
        CEP.TRC(SCCGWA, DCCUBRRC.INP_DATA.FUNC);
        if (DCCUBRRC.INP_DATA.FUNC == '0') {
            if (DCCUBRRC.INP_DATA.CARD_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NO_MISSING);
            }
        }
        if (DCCUBRRC.INP_DATA.FUNC == '1') {
            if (DCCUBRRC.INP_DATA.BR_NO == ' ') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_BR_NO_M_INPUT);
            }
        }
        if (DCCUBRRC.INP_DATA.FUNC == '2') {
            if (DCCUBRRC.INP_DATA.CARD_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NO_MISSING);
            }
            if (DCCUBRRC.INP_DATA.BR_NO == ' ') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_BR_NO_M_INPUT);
            }
        }
    }
    public void B002_GET_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        CEP.TRC(SCCGWA, DCCUBRRC.INP_DATA.CARD_NO);
        DCRCDDAT.KEY.CARD_NO = DCCUBRRC.INP_DATA.CARD_NO;
        T000_READ_DCTCDDAT();
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
        if (DCRCDDAT.CARD_STS == 'C') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_CANCEL_STS);
        }
        WS_BR_NO = DCRCDDAT.ISSU_BR;
    }
    public void B003_GET_AREACODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBRARC);
        CEP.TRC(SCCGWA, WS_BR_NO);
        DCRBRARC.KEY.BR_NO = WS_BR_NO;
        if (WS_FLG == '0') {
            DCRBRARC.KEY.BR_NO = DCCUBRRC.INP_DATA.BR_NO;
        }
        T000_READ_DCTBRARC();
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
        if (WS_FLG == '0') {
            WS_AREA_NO1 = DCRBRARC.AREA_NO;
        } else {
            WS_AREA_NO = DCRBRARC.AREA_NO;
        }
    }
    public void B004_DATA_OUTPUT() throws IOException,SQLException,Exception {
        DCCUBRRC.OUT_DATA.O_CARD_NO = DCCUBRRC.INP_DATA.CARD_NO;
        DCCUBRRC.OUT_DATA.O_BR_NO = WS_BR_NO;
        if (WS_FLG == '0') {
            DCCUBRRC.OUT_DATA.O_AREA_NO = WS_AREA_NO1;
        } else {
            DCCUBRRC.OUT_DATA.O_AREA_NO = WS_AREA_NO;
        }
        if (WS_AREA_NO == WS_AREA_NO1) {
            DCCUBRRC.OUT_DATA.O_CITY_FLG = '0';
        } else {
            if ((WS_AREA_NO != WS_AREA_NO1) 
                && (WS_AREA_NO != ' ' 
                && WS_AREA_NO1 != ' ')) {
                DCCUBRRC.OUT_DATA.O_CITY_FLG = '1';
            }
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
    }
    public void T000_READ_DCTBRARC() throws IOException,SQLException,Exception {
        DCTBRARC_RD = new DBParm();
        DCTBRARC_RD.TableName = "DCTBRARC";
        IBS.READ(SCCGWA, DCRBRARC, DCTBRARC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTBRARC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
