package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUCDDT {
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    String WS_ERR_MSG = " ";
    char WS_FUNC_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DCCUCDDT DCCUCDDT;
    public void MP(SCCGWA SCCGWA, DCCUCDDT DCCUCDDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUCDDT = DCCUCDDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCZUCDDT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT_DATA();
        if (WS_FUNC_FLAG == 'T') {
            B010_CHG_CARD_TYPE();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, DCCUCDDT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B001_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUCDDT.CARD_NO);
        CEP.TRC(SCCGWA, DCCUCDDT.TYPE_FLG);
        if (DCCUCDDT.FUNC_FLG != 'T') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_ERR, DCCUCDDT.RC);
            S000_ERR_MSG_PROC();
        } else {
            WS_FUNC_FLAG = 'T';
        }
        if (DCCUCDDT.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO, DCCUCDDT.RC);
            S000_ERR_MSG_PROC();
        }
        if (DCCUCDDT.TYPE_FLG != '2' 
            && DCCUCDDT.TYPE_FLG != '3') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHG_CARD_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCUCDDT.CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READ_DCTCDDAT_UPDATE();
        if (DCRCDDAT.CARD_STS == 'C') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED);
        }
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_TYP);
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (!DCRCDDAT.CARD_TYP.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            && DCCUCDDT.TYPE_FLG == '3') {
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            DCRCDDAT.CARD_TYP = DCRCDDAT.CARD_TYP.substring(0, 3 - 1) + "1" + DCRCDDAT.CARD_TYP.substring(3 + 1 - 1);
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTCDDAT();
        }
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (!DCRCDDAT.CARD_TYP.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && DCCUCDDT.TYPE_FLG == '2') {
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            DCRCDDAT.CARD_TYP = DCRCDDAT.CARD_TYP.substring(0, 2 - 1) + "1" + DCRCDDAT.CARD_TYP.substring(2 + 1 - 1);
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTCDDAT();
        }
    }
    public void T000_READ_DCTCDDAT_UPDATE() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCUCDDT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCUCHCD=");
            CEP.TRC(SCCGWA, DCCUCDDT);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
