package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUQSAC {
    int JIBS_tmp_int;
    DBParm DCTSPAC_RD;
    DBParm DCTBINPM_RD;
    DBParm DCTACLNK_RD;
    DBParm DCTIAACR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    char WS_BINPM_INFO = ' ';
    char WS_ACLNK_INFO = ' ';
    char WS_IAACR_INFO = ' ';
    char WS_SPAC_INFO = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRBINPM DCRBINPM = new DCRBINPM();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DCRACLNK DCRACLNK = new DCRACLNK();
    DCRSPAC DCRSPAC = new DCRSPAC();
    SCCGWA SCCGWA;
    DCCUQSAC DCCUQSAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCUQSAC DCCUQSAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUQSAC = DCCUQSAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUQSAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        B020_INQ_AC_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        if (DCCUQSAC.INP_DATA.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ACNO_MUST_INPUT, DCCUQSAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCUQSAC.INP_DATA.SEQ == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_QAC_SEQ_MUST_INPUT, DCCUQSAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBINPM);
        IBS.init(SCCGWA, DCRACLNK);
        IBS.init(SCCGWA, DCRIAACR);
        if (DCCUQSAC.INP_DATA.AC == null) DCCUQSAC.INP_DATA.AC = "";
        JIBS_tmp_int = DCCUQSAC.INP_DATA.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DCCUQSAC.INP_DATA.AC += " ";
        if (DCCUQSAC.INP_DATA.AC.substring(0, 4).equalsIgnoreCase("9111")) {
            CEP.TRC(SCCGWA, "149111");
            if (DCCUQSAC.INP_DATA.AC == null) DCCUQSAC.INP_DATA.AC = "";
            JIBS_tmp_int = DCCUQSAC.INP_DATA.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DCCUQSAC.INP_DATA.AC += " ";
            DCRBINPM.KEY.BIN = DCCUQSAC.INP_DATA.AC.substring(0, 4);
        } else {
            CEP.TRC(SCCGWA, "666666");
            if (DCCUQSAC.INP_DATA.AC == null) DCCUQSAC.INP_DATA.AC = "";
            JIBS_tmp_int = DCCUQSAC.INP_DATA.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DCCUQSAC.INP_DATA.AC += " ";
            DCRBINPM.KEY.BIN = DCCUQSAC.INP_DATA.AC.substring(0, 6);
        }
        CEP.TRC(SCCGWA, DCRBINPM.KEY.BIN);
        T00_READ_DCTBINPM();
        if (pgmRtn) return;
        if (WS_BINPM_INFO == 'Y') {
            DCRACLNK.KEY.CARD_NO = DCCUQSAC.INP_DATA.AC;
            T00_READ_DCTACLNK();
            if (pgmRtn) return;
        }
        if (WS_ACLNK_INFO == 'Y') {
            DCRIAACR.KEY.VIA_AC = DCRACLNK.VIA_AC;
        } else {
            DCRIAACR.KEY.VIA_AC = DCCUQSAC.INP_DATA.AC;
        }
        DCRIAACR.KEY.SEQ = DCCUQSAC.INP_DATA.SEQ;
        T00_READ_DCTIAACR();
        if (pgmRtn) return;
        if (WS_IAACR_INFO == 'Y') {
            DCCUQSAC.OUTP_DATA.SUB_AC = DCRIAACR.SUB_AC;
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_ACR_RCD_NOT_FND, DCCUQSAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTSPAC() throws IOException,SQLException,Exception {
        DCTSPAC_RD = new DBParm();
        DCTSPAC_RD.TableName = "DCTSPAC";
        IBS.READ(SCCGWA, DCRSPAC, DCTSPAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SPAC_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SPAC_INFO = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ DCTSPAC ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTSPAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        IBS.READ(SCCGWA, DCRBINPM, DCTBINPM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BINPM_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BINPM_INFO = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ DCTBINPM ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTBINPM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        IBS.READ(SCCGWA, DCRACLNK, DCTACLNK_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACLNK_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACLNK_INFO = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ DCTACLNK ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        IBS.READ(SCCGWA, DCRIAACR, DCTIAACR_RD);
        CEP.TRC(SCCGWA, DCRIAACR.KEY.VIA_AC);
        CEP.TRC(SCCGWA, DCRIAACR.KEY.SEQ);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IAACR_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IAACR_INFO = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ DCTIAACR ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
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
