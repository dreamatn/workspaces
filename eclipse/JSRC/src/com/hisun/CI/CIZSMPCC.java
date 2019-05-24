package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSMPCC {
    int PDM_CORP_NM_LEN;
    DBParm CITBAS_RD;
    DBParm CITPDM_RD;
    DBParm CITADR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRPDM CIRPDM = new CIRPDM();
    CIRPDM CIRPDMN = new CIRPDM();
    CIRPDM CIRPDMO = new CIRPDM();
    CIRADR CIRADR = new CIRADR();
    CICCMCK CICCMCK = new CICCMCK();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICSMADR CICSMADR = new CICSMADR();
    CICCGHIS CICCGHIS = new CICCGHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSMPCC CICSMPCC;
    public void MP(SCCGWA SCCGWA, CICSMPCC CICSMPCC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSMPCC = CICSMPCC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSMPCC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMPCC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_WRITE_NORMAL_INFO();
        if (pgmRtn) return;
        B030_WRITE_ADR_INFO();
        if (pgmRtn) return;
        B100_WRITE_HIS_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMPCC.DATA.CI_NO);
        if (CICSMPCC.DATA.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICSMPCC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_WRITE_NORMAL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSMPCC.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICSMPCC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        if (CIRBAS.CI_TYP != '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户类型不符");
        }
        CEP.TRC(SCCGWA, CIRBAS.CI_ATTR);
        if (CIRBAS.CI_ATTR != '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户性质不符");
        }
        IBS.init(SCCGWA, CIRPDM);
        IBS.init(SCCGWA, CIRPDMN);
        IBS.init(SCCGWA, CIRPDMO);
        CIRPDM.KEY.CI_NO = CICSMPCC.DATA.CI_NO;
        T000_READ_CITPDM_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_PDM_NOTFND, CICSMPCC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRPDM, CIRPDMO);
        if (CICSMPCC.DATA.OCCUP.trim().length() > 0) {
            CIRPDM.OCCUP = CICSMPCC.DATA.OCCUP;
        }
        if (CICSMPCC.DATA.PER_IND != 0) {
            CIRPDM.PER_INC = CICSMPCC.DATA.PER_IND;
        }
        if (CICSMPCC.DATA.FAM_IND != 0) {
            CIRPDM.FAM_INC = CICSMPCC.DATA.FAM_IND;
        }
        if (CICSMPCC.DATA.MARI.trim().length() > 0) {
            CIRPDM.MARI = CICSMPCC.DATA.MARI;
        }
        if (CICSMPCC.DATA.CORP_NM.trim().length() > 0) {
            CIRPDM.CORP_NM = CICSMPCC.DATA.CORP_NM;
            PDM_CORP_NM_LEN = CIRPDM.CORP_NM.length();
        }
        if (CICSMPCC.DATA.CORP_IND.trim().length() > 0) {
            CIRPDM.CORP_INDUS = CICSMPCC.DATA.CORP_IND;
        }
        CIRPDM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRPDM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRPDM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITPDM();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRPDM, CIRPDMN);
    }
    public void B030_WRITE_ADR_INFO() throws IOException,SQLException,Exception {
        if (CICSMPCC.DATA.CON_ADR.trim().length() > 0) {
            IBS.init(SCCGWA, CIRADR);
            CIRADR.KEY.CI_NO = CICSMPCC.DATA.CI_NO;
            CIRADR.KEY.ADR_TYPE = "111";
            T000_READ_CITADR();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.init(SCCGWA, CICSMADR);
                CICSMADR.DATA.CI_NO = CICSMPCC.DATA.CI_NO;
                CICSMADR.FUNC = 'A';
                CICSMADR.DATA.ADR_TYPE = "111";
                CICSMADR.DATA.ADR_CNTY = CICSMPCC.DATA.CON_CNTY;
                CICSMADR.DATA.ADR_L1 = CICSMPCC.DATA.CON_L1;
                CICSMADR.DATA.ADR_L2 = CICSMPCC.DATA.CON_L2;
                CICSMADR.DATA.ADR_L3 = CICSMPCC.DATA.CON_L3;
                CICSMADR.DATA.ADR_L4 = CICSMPCC.DATA.CON_L4;
                CICSMADR.DATA.ADR_L5 = CICSMPCC.DATA.CON_L5;
                CICSMADR.DATA.ADR_L6 = CICSMPCC.DATA.CON_L6;
                CICSMADR.DATA.ADR_L7 = CICSMPCC.DATA.CON_L7;
                CICSMADR.DATA.ADR_E2 = CICSMPCC.DATA.CON_E2;
                CICSMADR.DATA.ADR_NM = CICSMPCC.DATA.CON_ADR;
                CICSMADR.DATA.ADR_POST = CICSMPCC.DATA.CON_POST;
                S000_CALL_CIZSMADR();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, CICSMADR);
                CICSMADR.DATA.CI_NO = CICSMPCC.DATA.CI_NO;
                CICSMADR.FUNC = 'M';
                CICSMADR.DATA.ADR_TYPE = "111";
                CICSMADR.DATA.ADR_CNTY = CICSMPCC.DATA.CON_CNTY;
                CICSMADR.DATA.ADR_L1 = CICSMPCC.DATA.CON_L1;
                CICSMADR.DATA.ADR_L2 = CICSMPCC.DATA.CON_L2;
                CICSMADR.DATA.ADR_L3 = CICSMPCC.DATA.CON_L3;
                CICSMADR.DATA.ADR_L4 = CICSMPCC.DATA.CON_L4;
                CICSMADR.DATA.ADR_L5 = CICSMPCC.DATA.CON_L5;
                CICSMADR.DATA.ADR_L6 = CICSMPCC.DATA.CON_L6;
                CICSMADR.DATA.ADR_L7 = CICSMPCC.DATA.CON_L7;
                CICSMADR.DATA.ADR_E2 = CICSMPCC.DATA.CON_E2;
                CICSMADR.DATA.ADR_NM = CICSMPCC.DATA.CON_ADR;
                CICSMADR.DATA.ADR_POST = CICSMPCC.DATA.CON_POST;
                S000_CALL_CIZSMADR();
                if (pgmRtn) return;
            }
        }
        if (CICSMPCC.DATA.COM_ADR.trim().length() > 0) {
            IBS.init(SCCGWA, CIRADR);
            CIRADR.KEY.CI_NO = CICSMPCC.DATA.CI_NO;
            CIRADR.KEY.ADR_TYPE = "115";
            T000_READ_CITADR();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.init(SCCGWA, CICSMADR);
                CICSMADR.DATA.CI_NO = CICSMPCC.DATA.CI_NO;
                CICSMADR.FUNC = 'A';
                CICSMADR.DATA.ADR_TYPE = "115";
                CICSMADR.DATA.ADR_CNTY = CICSMPCC.DATA.COM_CNTY;
                CICSMADR.DATA.ADR_L1 = CICSMPCC.DATA.COM_L1;
                CICSMADR.DATA.ADR_L2 = CICSMPCC.DATA.COM_L2;
                CICSMADR.DATA.ADR_L3 = CICSMPCC.DATA.COM_L3;
                CICSMADR.DATA.ADR_L4 = CICSMPCC.DATA.COM_L4;
                CICSMADR.DATA.ADR_L5 = CICSMPCC.DATA.COM_L5;
                CICSMADR.DATA.ADR_L6 = CICSMPCC.DATA.COM_L6;
                CICSMADR.DATA.ADR_L7 = CICSMPCC.DATA.COM_L7;
                CICSMADR.DATA.ADR_E2 = CICSMPCC.DATA.COM_E2;
                CICSMADR.DATA.ADR_NM = CICSMPCC.DATA.COM_ADR;
                CICSMADR.DATA.ADR_POST = CICSMPCC.DATA.COM_POST;
                S000_CALL_CIZSMADR();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, CICSMADR);
                CICSMADR.DATA.CI_NO = CICSMPCC.DATA.CI_NO;
                CICSMADR.FUNC = 'M';
                CICSMADR.DATA.ADR_TYPE = "115";
                CICSMADR.DATA.ADR_CNTY = CICSMPCC.DATA.COM_CNTY;
                CICSMADR.DATA.ADR_L1 = CICSMPCC.DATA.COM_L1;
                CICSMADR.DATA.ADR_L2 = CICSMPCC.DATA.COM_L2;
                CICSMADR.DATA.ADR_L3 = CICSMPCC.DATA.COM_L3;
                CICSMADR.DATA.ADR_L4 = CICSMPCC.DATA.COM_L4;
                CICSMADR.DATA.ADR_L5 = CICSMPCC.DATA.COM_L5;
                CICSMADR.DATA.ADR_L6 = CICSMPCC.DATA.COM_L6;
                CICSMADR.DATA.ADR_L7 = CICSMPCC.DATA.COM_L7;
                CICSMADR.DATA.ADR_E2 = CICSMPCC.DATA.COM_E2;
                CICSMADR.DATA.ADR_NM = CICSMPCC.DATA.COM_ADR;
                CICSMADR.DATA.ADR_POST = CICSMPCC.DATA.COM_POST;
                S000_CALL_CIZSMADR();
                if (pgmRtn) return;
            }
        }
        if (CICSMPCC.DATA.FAR_ADR.trim().length() > 0) {
            IBS.init(SCCGWA, CIRADR);
            CIRADR.KEY.CI_NO = CICSMPCC.DATA.CI_NO;
            CIRADR.KEY.ADR_TYPE = "114";
            T000_READ_CITADR();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.init(SCCGWA, CICSMADR);
                CICSMADR.DATA.CI_NO = CICSMPCC.DATA.CI_NO;
                CICSMADR.FUNC = 'A';
                CICSMADR.DATA.ADR_TYPE = "114";
                CICSMADR.DATA.ADR_CNTY = CICSMPCC.DATA.FAR_CNTY;
                CICSMADR.DATA.ADR_L1 = CICSMPCC.DATA.FAR_L1;
                CICSMADR.DATA.ADR_L2 = CICSMPCC.DATA.FAR_L2;
                CICSMADR.DATA.ADR_L3 = CICSMPCC.DATA.FAR_L3;
                CICSMADR.DATA.ADR_L4 = CICSMPCC.DATA.FAR_L4;
                CICSMADR.DATA.ADR_L5 = CICSMPCC.DATA.FAR_L5;
                CICSMADR.DATA.ADR_L6 = CICSMPCC.DATA.FAR_L6;
                CICSMADR.DATA.ADR_L7 = CICSMPCC.DATA.FAR_L7;
                CICSMADR.DATA.ADR_E2 = CICSMPCC.DATA.FAR_E2;
                CICSMADR.DATA.ADR_NM = CICSMPCC.DATA.FAR_ADR;
                CICSMADR.DATA.ADR_POST = CICSMPCC.DATA.FAR_POST;
                S000_CALL_CIZSMADR();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, CICSMADR);
                CICSMADR.DATA.CI_NO = CICSMPCC.DATA.CI_NO;
                CICSMADR.FUNC = 'M';
                CICSMADR.DATA.ADR_TYPE = "114";
                CICSMADR.DATA.ADR_CNTY = CICSMPCC.DATA.FAR_CNTY;
                CICSMADR.DATA.ADR_L1 = CICSMPCC.DATA.FAR_L1;
                CICSMADR.DATA.ADR_L2 = CICSMPCC.DATA.FAR_L2;
                CICSMADR.DATA.ADR_L3 = CICSMPCC.DATA.FAR_L3;
                CICSMADR.DATA.ADR_L4 = CICSMPCC.DATA.FAR_L4;
                CICSMADR.DATA.ADR_L5 = CICSMPCC.DATA.FAR_L5;
                CICSMADR.DATA.ADR_L6 = CICSMPCC.DATA.FAR_L6;
                CICSMADR.DATA.ADR_L7 = CICSMPCC.DATA.FAR_L7;
                CICSMADR.DATA.ADR_E2 = CICSMPCC.DATA.FAR_E2;
                CICSMADR.DATA.ADR_NM = CICSMPCC.DATA.FAR_ADR;
                CICSMADR.DATA.ADR_POST = CICSMPCC.DATA.FAR_POST;
                S000_CALL_CIZSMADR();
                if (pgmRtn) return;
            }
        }
    }
    public void B100_WRITE_HIS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCGHIS);
        CICCGHIS.DATA.TABLE_NM = "CITPDM";
        CICCGHIS.DATA.OLD_DATA_LEN = 179;
        CICCGHIS.DATA.NEW_DATA_LEN = 179;
        CICCGHIS.DATA.OLD_DATA_PTR = CIRPDMO;
        CICCGHIS.DATA.NEW_DATA_PTR = CIRPDMN;
        S000_CALL_CIZCGHIS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = CICSMPCC.DATA.CI_NO;
        BPCPNHIS.INFO.FMT_ID = "CIRPDM";
        BPCPNHIS.INFO.FMT_ID_LEN = 179;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRPDMN;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRPDMO;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZCMCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-COM-CHK", CICCMCK);
        if (CICCMCK.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCMCK.RC);
        }
    }
    public void S000_CALL_CIZCGHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MOD-HISTORY", CICCGHIS);
        if (CICCGHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCGHIS.RC);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
    } //FROM #ENDIF
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_CALL_CIZSMADR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-M-ADR-INF", CICSMADR);
        if (CICSMADR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSMADR.RC);
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITPDM_UPD() throws IOException,SQLException,Exception {
        CITPDM_RD = new DBParm();
        CITPDM_RD.TableName = "CITPDM";
        CITPDM_RD.upd = true;
        IBS.READ(SCCGWA, CIRPDM, CITPDM_RD);
    }
    public void T000_REWRITE_CITPDM() throws IOException,SQLException,Exception {
        CITPDM_RD = new DBParm();
        CITPDM_RD.TableName = "CITPDM";
        IBS.REWRITE(SCCGWA, CIRPDM, CITPDM_RD);
    }
    public void T000_READ_CITADR() throws IOException,SQLException,Exception {
        CITADR_RD = new DBParm();
        CITADR_RD.TableName = "CITADR";
        IBS.READ(SCCGWA, CIRADR, CITADR_RD);
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
