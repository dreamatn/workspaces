package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSCRC {
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    char WS_BAS_DEAL_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSCRC CICSCRC;
    public void MP(SCCGWA SCCGWA, CICSCRC CICSCRC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSCRC = CICSCRC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZSCRC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSCRC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (WS_BAS_DEAL_FLG == 'Y') {
            B020_REWRITE_CIRBAS_INFO();
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'M';
            R000_SAVE_HIS_PROC();
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSCRC.DATA.CI_NO;
        T000_READ_CITBAS_BY_CINO_UPD();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_NOTFND);
        } else {
            if (CIRBAS.ID_TYPE.equalsIgnoreCase(CICSCRC.DATA.ID_TYPE) 
                && CIRBAS.ID_NO.equalsIgnoreCase(CICSCRC.DATA.ID_NO) 
                && CIRBAS.CI_NM.equalsIgnoreCase(CICSCRC.DATA.CI_NM)) {
                if (CIRBAS.CI_ATTR == '0') {
                    WS_BAS_DEAL_FLG = 'Y';
                } else {
                    if (CIRBAS.CI_ATTR == '2' 
                        || CIRBAS.CI_ATTR == '6') {
                    } else {
                        CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ATTR_UNMATCH, "该客户不是预�?客户");
                    }
                }
            } else {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH, "客户三要素输入不匹配");
            }
        }
    }
    public void B020_REWRITE_CIRBAS_INFO() throws IOException,SQLException,Exception {
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = "001" + CIRBAS.STSW.substring(3);
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITBAS();
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = CIRBAS.KEY.CI_NO;
        BPCPNHIS.INFO.FMT_ID = "CIRBAS";
        BPCPNHIS.INFO.FMT_ID_LEN = 568;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRBAS;
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void T000_READ_CITBAS_BY_CINO_UPD() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "CI_NO";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
    }
    public void T000_REWRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.REWRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
